package br.org.prismaCamara.util.xml;

import java.util.HashMap;
import java.util.Map;

public class CaracteresUtil {

	static Map<String, String> ms = new HashMap();
	
	static {
		ms.put("Ö","Í");
		ms.put("","Ç");
		ms.put("å","Õ");
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
