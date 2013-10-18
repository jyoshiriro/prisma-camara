package br.org.prismaCamara.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import br.org.prismaCamara.modelo.Deputado;

import com.ximpleware.AutoPilot;
import com.ximpleware.EOFException;
import com.ximpleware.EncodingException;
import com.ximpleware.EntityException;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

public class LerXmlCota {
	
	/**
	 * @param conteudoZip
	 * @param deputadosMapeados
	 * @return {@link List} de {@link Map} com os atributos e seus valores
	 * @throws EncodingException
	 * @throws EOFException
	 * @throws EntityException
	 * @throws ParseException
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 * @throws java.text.ParseException
	 * @throws IOException 
	 */
	public List<Map> getNovasDespesas(byte[] conteudoZip, Set<Deputado> deputadosMapeados, String nomeArquivoTemp) throws EncodingException, EOFException, EntityException, ParseException, XPathParseException, XPathEvalException, NavException, java.text.ParseException, IOException {

		File ftemp = new File(nomeArquivoTemp+".zip");
		FileUtils.writeByteArrayToFile(ftemp, conteudoZip); 
		ZipFile zipFile = new ZipFile(ftemp);
		
		Enumeration<? extends ZipEntry> entries = zipFile.getEntries();
        ZipEntry entry = entries.nextElement();
        File entryDestination = new File(nomeArquivoTemp+".xml",  entry.getName());
        entryDestination.getParentFile().mkdirs();
        InputStream in = zipFile.getInputStream(entry);
        OutputStream out = new FileOutputStream(entryDestination);
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
        
		FileInputStream fis = new FileInputStream(entryDestination);
		byte[] conteudoXml = new byte[(int) entryDestination.length()];
		fis.read(conteudoXml);
		
		List<Map> despesas = new ArrayList<Map>();
		
		VTDGen vgGeral = new VTDGen();
		vgGeral.setDoc(conteudoXml); 
		vgGeral.parse(true);
		VTDNav vnGeral = vgGeral.getNav();
		
		AutoPilot ap = new AutoPilot(vnGeral);
		ap.selectXPath("//orgao//DESPESAS//DESPESA");
		
		Map<Integer,Date> mapDeputados = new HashMap<Integer, Date>();
		Calendar cLimite = Calendar.getInstance();
		cLimite.add(Calendar.MONTH, -1);
		Date limiteInicial = cLimite.getTime(); 
		for (Deputado dep:deputadosMapeados) {
			mapDeputados.put(dep.getMatricula(), dep.getUltimoDiaGasto()!=null?dep.getUltimoDiaGasto():limiteInicial);
		}
		
		Map<String,String> mapaXmlDespesa = new LinkedHashMap<String, String>();
		mapaXmlDespesa.put("nuCarteiraParlamentar", "deputado.matricula");
		mapaXmlDespesa.put("txtDescricao", "txtDescricao");
		mapaXmlDespesa.put("txtBeneficiario", "txtBeneficiario");
		mapaXmlDespesa.put("txtCNPJCPF", "txtCNPJCPF");
		mapaXmlDespesa.put("txtNumero", "txtNumero");
		mapaXmlDespesa.put("datEmissao", "dataEmissao");
		mapaXmlDespesa.put("vlrDocumento", "valor");
		mapaXmlDespesa.put("vlrGlosa", "valorGlosa");
		mapaXmlDespesa.put("numParcela", "numParcela");
		
		List<String> nomesAtributos = new ArrayList<String>(mapaXmlDespesa.keySet());
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T00:00:00'");
		int result = 0;
		int matZero =0;
		Set<String> descricoes = new LinkedHashSet(); // TODO: temporário! Tirar de posi de identificra os caracteres especiais
		loopDespesa:while (result!=-1) { // Loop por Despesa de Deputado 
			result = ap.evalXPath();
			Map<String, Object> mapValores = new LinkedHashMap<String, Object>();
			Integer ultimaMatricula = 0;
			Date ultimaDataTodos = null;
			loopAtributos:for (int i=0;i<nomesAtributos.size();i++) { // loop de atribuitos de cada despesa de Deputado
				Object valor = lerValor(i!=0, nomesAtributos.get(i), vnGeral);
				try {
					switch (i) {
					
						case 0: // validação de matrícula válida e mapeada
							if ((valor==null || valor.equals("") || valor.equals("0")) || (!mapDeputados.containsKey(Integer.valueOf(valor.toString().trim()))) ) {
								vnGeral.toElement(VTDNav.PARENT);
								continue loopDespesa;
							}
							ultimaMatricula = Integer.valueOf(valor.toString());
							mapValores.put(nomesAtributos.get(i), ultimaMatricula);
							break;
							
						case 1:	// Ajuste de caracteres especiais nas descrições (1 e 2)
						case 2: 
							descricoes.add(valor.toString());
							String corrigido = CaracteresUtil.corrigirEspeciais(valor.toString().trim());
							if (!corrigido.equals(valor.toString())) {
								System.out.println(" - "+valor);
								System.out.println(" - "+corrigido+" <-\n");
							}
							mapValores.put(nomesAtributos.get(i), corrigido);
							break;
							
						case 5: // validação de data de novo gasto  
							Date dataGastoAtual = formato.parse(valor.toString().trim());
							if ( (mapDeputados.get(ultimaMatricula)!=null) && (!dataGastoAtual.after(mapDeputados.get(ultimaMatricula))) ) {
								mapValores.clear();
								System.out.println("saiu pela data de gasto");
								break loopAtributos;
							}
							valor = dataGastoAtual;
							mapValores.put(nomesAtributos.get(i), valor);
							ultimaDataTodos = dataGastoAtual;
							break;
						
						case 6: // valores Double (6 e 7)
							// 6==7
						case 7:
							mapValores.put(nomesAtributos.get(i), Double.valueOf(valor.toString()));
							break;
							
						case 8:
							mapValores.put(nomesAtributos.get(i), Integer.valueOf(valor.toString()));
							break;
							
						default:
							mapValores.put(nomesAtributos.get(i), valor.toString());
							break;
					}
				} catch (Exception e) {
					System.err.println("Erro no formado de algum dado de Despesa de Deputado: "+e.getMessage()
							+". Última Matrícula registrada: "+ultimaMatricula
							+". Última Data registrada: "+ultimaDataTodos);
					e.printStackTrace();
					vnGeral.toElement(VTDNav.PARENT);
					System.out.println("saiu por erro de conversão");					
					continue loopDespesa;
				}
				
			} // loop de preenchimento de valores de cada despesa
			
			if (!mapValores.isEmpty()) {
				Map valoresDespesa = new HashMap();
				for (String chave:nomesAtributos) {
					 valoresDespesa.put(mapaXmlDespesa.get(chave),mapValores.get(chave));
				}
				despesas.add(valoresDespesa);
			}
			
		    vnGeral.toElement(VTDNav.PARENT);
	
		} // Loop por Despesa de Deputado
		
		/*for (String string : descricoes) {
			System.out.println("->"+string+"<-");
		}*/
		vgGeral.clear();		
		zipFile.close();
		ftemp.delete();
		new File(nomeArquivoTemp+".xml").delete();
		
		return despesas;
	}

	/**
	 * @param proximo Se false, pega o filho (usar na primeira subtag de uma tag)
	 * @param nome Nome da tag a pegar o valor
	 * @param vn Navegador de nó atual
	 * @return Conteúdo da tag
	 * @throws NavException
	 */
	private String lerValor(boolean proximo,String nome, VTDNav vn) throws NavException {
		vn.toElement(proximo?VTDNav.NEXT_SIBLING:VTDNav.FIRST_CHILD,nome);
		int val = vn.getText();
		String valor = val!=-1?vn.toNormalizedString(val):""; 
        return valor;
	}

}
