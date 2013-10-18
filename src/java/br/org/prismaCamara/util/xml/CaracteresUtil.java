package br.org.prismaCamara.util.xml;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaracteresUtil {

	static Map<String, String> ms = new LinkedHashMap<String, String>();
	
	static {
		ms.put("Ö","Í");
		ms.put("Ç","Ã");
		ms.put("å","ÇÕ");
//		ms.put("å","Õ");
		ms.put("µ","Á");
		ms.put("¢","ó");
		ms.put("à","Ó");
		ms.put("Æ","ã");
		ms.put("Ò","Ê");
		ms.put("â","Ô");
	}
	
	static String corrigirEspeciais(String original) {
		StringBuffer sb = new StringBuffer(original);
		for (String chave:ms.keySet()) {
			String novo = sb.toString().replace(chave, ms.get(chave));
			sb = new StringBuffer(novo);
		}
		return sb.toString();
	}

}
