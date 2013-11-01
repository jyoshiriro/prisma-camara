/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.util

import br.org.prismaCamara.modelo.Deputado
import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import org.imgscalr.Scalr

/**
 * Classe utilitária para uso de imagens locais no Servidor 
 * @author jyoshiriro
 *
 */
class ImagensUtil {
	
	/**
	 * Retorna o array de bytes de uma imagem local no servidor a partir do nome indicado em "nomeArquivo"
	 * @param nomeArquivo
	 * @return o array de bytes do arquivo ou <b>null</b> se o arquivo não existe
	 */
	static byte[] getImagemLocal(String nomeArquivo) {
		def arquivo = new File(nomeArquivo)
		if (arquivo.exists()) {
			arquivo.bytes
		} else {
			null
		}
	}

	/**
	 * Retorna o array de bytes da miniatura da imagem da URL indicada em "urlImagemOriginal"
	 * @param urlImagemOriginal
	 * @param nomeArquivo Nome do arquivo que será salvo localmente no servidor. Se <b>null</b>, nenhum arquivo será salvo.
	 * @return
	 */
	static byte[] getMiniatura(String urlImagemOriginal, String nomeArquivo) {
		getMiniatura(urlImagemOriginal.toURL().bytes, nomeArquivo)
	}
	
	/**
	 * Retorna o array de bytes da miniatura da imagem do array de bytes do parâmetro "original"
	 * @param original
	 * @param nomeArquivo Nome do arquivo que será salvo localmente no servidor. Se <b>null</b>, nenhum arquivo será salvo.
	 * @return O array de bytes ou <b>null</b>
	 */
	static byte[] getMiniatura(byte[] original, String nomeArquivo) {

		def imageIn = ImageIO.read(new ByteArrayInputStream(original));
		if ((!original) || (!imageIn)) {
			return null
		}		
		BufferedImage scaledImage = Scalr.resize(imageIn, 72).getSubimage(3, 7, 48, 48);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream()
		ImageIO.write(scaledImage, "jpg", bos);
		
		def bmini = bos.buf
		
		if (nomeArquivo) {
			new File(nomeArquivo).bytes = bmini
		}
		
		bmini
	}
}
