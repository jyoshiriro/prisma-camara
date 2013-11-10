/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.util.xml;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.util.ZipUtil;

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
		
		byte[] conteudoXml = ZipUtil.descompactarAnoAtualZip(conteudoZip);
		
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
							if (valor!=null && (valor.equals("") || valor.equals("0"))) {
//								System.err.println("Despesa sem Deputado!!! "+ lerValor(i!=0, nomesAtributos.get(5), vnGeral));
								System.err.println("Despesa sem Deputado!!! ");
							}
							if ((valor==null || valor.equals("") || valor.equals("0")) || (!mapDeputados.containsKey(Integer.valueOf(valor.toString().trim()))) ) {
								vnGeral.toElement(VTDNav.PARENT);
								continue loopDespesa;
							}
							ultimaMatricula = Integer.valueOf(valor.toString());
							mapValores.put(nomesAtributos.get(i), ultimaMatricula);
							break;
							
						case 1:	// Ajuste de caracteres especiais nas descrições (1 e 2)
						case 2: 
							String corrigido = CaracteresUtil.corrigirEspeciais(valor.toString().trim());
							mapValores.put(nomesAtributos.get(i), corrigido);
							break;
							
						case 5: // validação de data de novo gasto  
							Date dataGastoAtual = formato.parse(valor.toString().trim());
							if ( (mapDeputados.get(ultimaMatricula)!=null) && (!dataGastoAtual.after(mapDeputados.get(ultimaMatricula))) ) {
								mapValores.clear();
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
		
		vgGeral.clear();		
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
