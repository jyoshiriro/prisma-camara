package br.org.prismaCamara.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

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
	 * @param conteudoXml
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
	public List<Map> getNovasDespesas(byte[] conteudoXml, Set<Deputado> deputadosMapeados) throws EncodingException, EOFException, EntityException, ParseException, XPathParseException, XPathEvalException, NavException, java.text.ParseException, IOException {
		
		if (conteudoXml==null) {
			String caminho = "C:/Users/Administrador/Documents/yoshi/partiubrasilia/workspace/prisma-camara/testeCotaTudo.xml";
			File f = new File(caminho);
	
			FileInputStream fis = new FileInputStream(f);
			conteudoXml = new byte[(int) f.length()];
			fis.read(conteudoXml);
		}
		
		List<Map> despesas = new ArrayList<Map>();
		
		VTDGen vgGeral = new VTDGen();
		vgGeral.setDoc(conteudoXml); 
		vgGeral.parse(true);
		VTDNav vnGeral = vgGeral.getNav();
		
		AutoPilot ap = new AutoPilot(vnGeral);
		ap.selectXPath("//orgao//DESPESAS//DESPESA");
		
		Map<Integer,Date> mapDeputados = new HashMap<Integer, Date>(); 
		for (Deputado dep:deputadosMapeados) {
			mapDeputados.put(dep.getMatricula(), dep.getUltimoDiaGasto());
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
		l1:while (result!=-1) {
			result = ap.evalXPath();
			
			Map<String, Object> mapValores = new LinkedHashMap<String, Object>();

			Integer ultimaMatricula = 0;
			l2:for (int i=0;i<nomesAtributos.size();i++) {
				Object valor = lerValor(i!=0, nomesAtributos.get(i), vnGeral);
				
				switch (i) {
				
					case 0: // validação de matrícula válida e mapeada
						if ((valor==null || valor.equals("") || valor.equals("0")) || (!mapDeputados.containsKey(Integer.valueOf(valor.toString().trim()))) ) {
							vnGeral.toElement(VTDNav.PARENT);
							continue l1;
						}
						ultimaMatricula = Integer.valueOf(valor.toString());
						mapValores.put(nomesAtributos.get(i), ultimaMatricula);
						break;
						
					case 5: // validação de data de novo gasto  
						Date dataGastoAtual = formato.parse(valor.toString().trim());
						if ( (mapDeputados.get(ultimaMatricula)!=null) && (!dataGastoAtual.after(mapDeputados.get(ultimaMatricula))) ) {
							vnGeral.toElement(VTDNav.PARENT);
							continue l1;
						}
						valor = dataGastoAtual;
						mapValores.put(nomesAtributos.get(i), valor);
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
			} // loop de preenchimento de valores de cada despesa
			
			Map valoresDespesa = new HashMap();
			for (String chave:nomesAtributos) {
				 valoresDespesa.put(mapaXmlDespesa.get(chave),mapValores.get(chave));
			}
			despesas.add(valoresDespesa);
			
		    vnGeral.toElement(VTDNav.PARENT);
	
		}
		
		vgGeral.clear();		
		
		return despesas;
	}

	public static void main(String[] args) throws IOException,
			EncodingException, EOFException, EntityException, ParseException,
			NavException, XPathParseException, XPathEvalException, java.text.ParseException {
		
		LerXmlCota l = new LerXmlCota();
		List deputados = new  ArrayList();
		Deputado d1 = new Deputado();
		d1.setMatricula(256);
		d1.setUltimoDiaGasto(new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2013"));
		Deputado d2 = new Deputado();
		d1.setMatricula(522);
		d1.setUltimoDiaGasto(new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2013"));
		deputados.add(d1);
		deputados.add(d2);
		
		Object despesas = l.getNovasDespesas(FileUtils.readFileToByteArray(new File("C:/Users/Administrador/Documents/yoshi/partiubrasilia/workspace/prisma-camara/testeCotaTudo.xml")), new HashSet<Deputado>(deputados));
		System.out.println(despesas);
		
/*		String caminho = "C:/Users/Administrador/Documents/yoshi/partiubrasilia/workspace/prisma-camara/testeCotaTudo.xml";
		File f = new File(caminho);

		FileInputStream fis = new FileInputStream(f);
		byte[] b = new byte[(int) f.length()];
		fis.read(b);

		VTDGen vgGeral = new VTDGen();
		vgGeral.setDoc(b); // precisa do byte[] 
		vgGeral.parse(true);
		VTDNav vnGeral = vgGeral.getNav();
		
		AutoPilot ap = new AutoPilot(vnGeral);
		ap.selectXPath("//orgao//DESPESAS//DESPESA");
		
		Map<Integer,Date> mapDeputados = new HashMap<Integer, Date>(); // vai virar par�metro
		mapDeputados.put(356, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2013"));
		mapDeputados.put(522, new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2013"));
		
		List<String> nomesAtributos = Arrays.asList("nuCarteiraParlamentar","txtDescricao","txtBeneficiario","txtCNPJCPF","txtNumero","datEmissao","vlrDocumento","vlrGlosa","numParcela");
		List<Map> listaMapas = new ArrayList<Map>();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T00:00:00'");
		int result = 0;
		l1:while (result!=-1) {
			result = ap.evalXPath();
			
			Map<String, String> mapValores = new LinkedHashMap<String, String>();

			Integer ultimaMatricula = 0;
			l2:for (int i=0;i<nomesAtributos.size();i++) {
				String valor = lerValor(i!=0, nomesAtributos.get(i), vnGeral);
				
				if (i==0) { 
					if ((valor==null || valor.equals("") || valor.equals("0")) || (!mapDeputados.containsKey(Integer.valueOf(valor.trim()))) ) {
						vnGeral.toElement(VTDNav.PARENT);
						continue l1;
					}
					ultimaMatricula = Integer.valueOf(valor);
				}
				
				if (i==5) { 
					Date dataGastoAtual = formato.parse(valor);
					if (!dataGastoAtual.after(mapDeputados.get(ultimaMatricula))) {
						vnGeral.toElement(VTDNav.PARENT);
						continue l1;
					}
				}
				
				mapValores.put(nomesAtributos.get(i), valor);
			}

			listaMapas.add(mapValores);
	        System.out.println(mapValores);
		    
		    vnGeral.toElement(VTDNav.PARENT);
			System.out.println();
		}
		
		vgGeral.clear();
		System.out.println("Novas despesas encontradas: "+listaMapas.size());*/
	}
	
	/**
	 * @param proximo Se false, pega o filho (usar na primeira subtag de uma tag)
	 * @param nome Nome da tag a pegar o valor
	 * @param vn Navegador de n� atual
	 * @return Conte�do da tag
	 * @throws NavException
	 */
	private String lerValor(boolean proximo,String nome, VTDNav vn) throws NavException {
		vn.toElement(proximo?VTDNav.NEXT_SIBLING:VTDNav.FIRST_CHILD,nome);
		int val = vn.getText();
		String valor = val!=-1?vn.toNormalizedString(val):""; 
        return valor;
	}

}
