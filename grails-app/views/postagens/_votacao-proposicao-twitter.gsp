Nova votação da ${prop.descricao} em ${votacao.dataHoraVotacao.format("dd/MM', 'HH:mm")}
${votacao.objVotacao.size()>39?votacao.objVotacao[0..35]+'...':votacao.objVotacao}
Maioria: "${mvotos.keySet().first()}" (${mvotos["${mvotos.keySet().first()}"].size()} votos)
${prop.urlDetalhesCurta}  Via http://goo.gl/fWAHmG