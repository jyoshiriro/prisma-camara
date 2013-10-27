package br.org.prismaCamara.util

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import CORBAphonetic.PhoneticTransformer;
import CORBAphonetic.PhoneticTransformerImpl;

class PesquisaFoneticaUtil {

	protected static PhoneticTransformerImpl phoneticTransformerImpl = new CORBAphonetic.PhoneticTransformerImpl();
	protected static PhoneticTransformer phoneticTransformer = new CORBAphonetic._tie_PhoneticTransformer(phoneticTransformerImpl, "phonTrans");
	
	protected static List<String> ignorados = new ArrayList<String>();
	protected static List<List<String>> nomesParecidos = new ArrayList<List<String>>();
	protected static Properties propSubstituicoesLetras, propSubstituicoesLetrasFinal;
	
	static {
		
		ignorados.add("DE");
		ignorados.add("DO");
		ignorados.add("DOS");
		ignorados.add("DA");
		ignorados.add("DAS");
		ignorados.add("E");
		ignorados.add("A");
		ignorados.add("À");
		ignorados.add("O");
		ignorados.add("OS");
		ignorados.add("NA");
		ignorados.add("NAS");
		ignorados.add("NO");
		ignorados.add("NOS");
		
		propSubstituicoesLetras = new Properties();
		try {
			propSubstituicoesLetras.load(Thread.currentThread().contextClassLoader.getResourceAsStream('substituicoesLetras.properties'));
		} catch (IOException e) {
			System.err.println("Falha ao carregar arquivo 'substituicoesLetras.properties'");
			e.printStackTrace();
		}
		
		propSubstituicoesLetrasFinal = new Properties();
		try {
			propSubstituicoesLetrasFinal.load(Thread.currentThread().contextClassLoader.getResourceAsStream("substituicoesLetrasFinal.properties"));
		} catch (IOException e) {
			System.err.println("Falha ao carregar arquivo 'substituicoesLetrasFinal.properties'");
			e.printStackTrace();
		}
		
		Properties pParecidos = new Properties();
		try {
			List<String> linhas = FileUtils.readLines(new File(Thread.currentThread().contextClassLoader.getResource("nomesParecidos.txt").getFile()));
			for (String linha : linhas) {
				if (linha.startsWith("#"))
					continue;
				List<String> palavrasNaLinha = new ArrayList<String>();
				for (String palavraNaLinha : Arrays.asList(linha.split(","))) {
					palavrasNaLinha.add(ajustarExcecoes(palavraNaLinha));
				}
				nomesParecidos.add(palavrasNaLinha);
			}
		} catch (IOException e) {
			System.err.println("Falha ao carregar arquivo 'nomesParecidos.txt'");
			e.printStackTrace();
		}

	}
	
	protected static String getFonemasParaIndexar(String fraseOriginal) {
		if (fraseOriginal==null) return null;
		StringBuilder sb = new StringBuilder(fraseOriginal.toUpperCase());
		StringUtil.substituirVarios(sb, " ", "-","/","_",".");
		String[] palavras = sb.toString().split(" ");
		StringBuilder fonemas = new StringBuilder();
		for (int i = 0; i < palavras.length; i++) {
			String palavra = palavras[i];
			if (ignorados.contains(palavra.toUpperCase()))
				continue;
			String fonema = phoneticTransformer.phoneticTransformation(ajustarExcecoes(palavra));
			fonemas.append(fonema);
			fonemas.append(" ");
		}
		return fonemas.toString();
	}
	
	protected static String getTermosFoneticosParaPesquisa(String termoPesquisa) {
		//termoPesquisa = new String(termoPesquisa.getBytes("ISO-8859-1"),"UTF-8");
		termoPesquisa = termoPesquisa.toUpperCase().replace("-", " ").replace("/", " ");
		
		List<String> termos = new ArrayList<String>();
		termos.add(termoPesquisa);
		
		List<String> palavras = new ArrayList<String>();
		for (String palavra:Arrays.asList(termoPesquisa.split(" "))) {
			if (!ignorados.contains(palavra))
				palavras.add(palavra);
		}
		
		StringBuilder termoFonetico = new StringBuilder("");

		Set<String> fonemasEncontrados = new HashSet<String>();
		for (int i = 0; i < palavras.size(); i++) {
			String palavra = palavras.get(i);
			
			palavra = ajustarExcecoes(palavra);
			
			List<String> palavrasEParecidos = getParecidos(palavra);
			if (palavrasEParecidos.size()>1) {
				termoFonetico.append("(");
				for (int j=0;j<palavrasEParecidos.size();j++) {
					String palavraP = phoneticTransformer.phoneticTransformation(palavrasEParecidos.get(j));
					if (fonemasEncontrados.add(palavraP)) {
						termoFonetico.append(palavraP);
						if (j < palavrasEParecidos.size()-1)
							termoFonetico.append(" OR ");
					}
				}
				termoFonetico.append(")");

			}
			else {
				String palavraP = phoneticTransformer.phoneticTransformation(palavra);
				if (fonemasEncontrados.add(palavraP)) {
					termoFonetico.append(palavraP);
				}
			}
			if (i<palavras.size()-1)
				termoFonetico.append(" AND ");
		}
		fonemasEncontrados.clear();
		fonemasEncontrados = null;
		return termoFonetico
	}
	
	/**
	 * Ajusta exceções em uma única palavra
	 * @param palavra
	 * @return
	 */
	public static String ajustarExcecoes(String palavra) {		
		StringBuilder sbPalavra = new StringBuilder(palavra);		
		StringUtil.aplicarAjustesPalavra(sbPalavra, propSubstituicoesLetras, propSubstituicoesLetrasFinal);			
		return sbPalavra.toString();
	}
	
	/**
	 * Retorna uma lista de "nomes parecidos", que obtém seus valores pelo conteúdo de "nomesParecidos.txt" e de outras regras definidas no código
	 * @return
	*/
	public static List<String> getParecidos(String palavra) {
		Set<String> parecidos = new HashSet<String>();
		palavra = ajustarExcecoes(palavra);
		for (List<String> linha : nomesParecidos) {
			if (linha.contains(palavra)) {
				parecidos.addAll(linha);
				//break;
			}
		}
		if (parecidos.isEmpty())
			parecidos.add(palavra);
		return new ArrayList<String>(parecidos);
		
	}
	
	static main(args) {
		println "Pesquisa: " + PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa("ALESSANDRO") + " " +  PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa("ALESSANDRO");
	} 
	
}
