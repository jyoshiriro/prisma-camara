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
package br.org.prismaCamara.test.unit



import org.junit.*
import grails.test.mixin.*

@TestFor(ProposicaoController)
@Mock(Proposicao)
class ProposicaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proposicao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.proposicaoInstanceList.size() == 0
        assert model.proposicaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.proposicaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.proposicaoInstance != null
        assert view == '/proposicao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proposicao/show/1'
        assert controller.flash.message != null
        assert Proposicao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proposicao/list'

        populateValidParams(params)
        def proposicao = new Proposicao(params)

        assert proposicao.save() != null

        params.id = proposicao.id

        def model = controller.show()

        assert model.proposicaoInstance == proposicao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proposicao/list'

        populateValidParams(params)
        def proposicao = new Proposicao(params)

        assert proposicao.save() != null

        params.id = proposicao.id

        def model = controller.edit()

        assert model.proposicaoInstance == proposicao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proposicao/list'

        response.reset()

        populateValidParams(params)
        def proposicao = new Proposicao(params)

        assert proposicao.save() != null

        // test invalid parameters in update
        params.id = proposicao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proposicao/edit"
        assert model.proposicaoInstance != null

        proposicao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proposicao/show/$proposicao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proposicao.clearErrors()

        populateValidParams(params)
        params.id = proposicao.id
        params.version = -1
        controller.update()

        assert view == "/proposicao/edit"
        assert model.proposicaoInstance != null
        assert model.proposicaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proposicao/list'

        response.reset()

        populateValidParams(params)
        def proposicao = new Proposicao(params)

        assert proposicao.save() != null
        assert Proposicao.count() == 1

        params.id = proposicao.id

        controller.delete()

        assert Proposicao.count() == 0
        assert Proposicao.get(proposicao.id) == null
        assert response.redirectedUrl == '/proposicao/list'
    }
}
