package br.org.prismaCamara.util

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream


/**
 * Classe Utilitária para compactar e descompactar uma {@link String}
 * @author jyoshiriro
 *
 */
class ZipUtil {

	/**
	 * Compacta uma string em um array de bytes
	 * @param original
	 * @return O array de byte compactado ou <b>null</b> caso o original seja vazio
	 */
	static byte[] compactar(String original)  {
		if (!original)
			return null
		def targetStream = new ByteArrayOutputStream()
		def zipStream = new GZIPOutputStream(targetStream)
		zipStream.write(original.bytes)
		zipStream.close()
		def zipado = targetStream.toByteArray()
		targetStream.close()
		zipado
	}
	
	
	/**
	 * Descompacta um array de bytes em uma String
	 * @param original
	 * @return A String descompactada ou <b>null</b> caso o original seja vazio 
	 */
	static String descompactar(byte[] original)  {
		if (!original)
			return null
		GZIPInputStream zipStream = new GZIPInputStream(new ByteArrayInputStream(original))
		def descompactado = zipStream.text
		zipStream.close()
		descompactado
	}
	
	
}
