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
package br.org.prismaCamara.test.unit


import org.junit.*
import grails.test.mixin.*

@TestFor(VotacaoController)
@Mock(Votacao)
class VotacaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/votacao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.votacaoInstanceList.size() == 0
        assert model.votacaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.votacaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.votacaoInstance != null
        assert view == '/votacao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/votacao/show/1'
        assert controller.flash.message != null
        assert Votacao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/votacao/list'

        populateValidParams(params)
        def votacao = new Votacao(params)

        assert votacao.save() != null

        params.id = votacao.id

        def model = controller.show()

        assert model.votacaoInstance == votacao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/votacao/list'

        populateValidParams(params)
        def votacao = new Votacao(params)

        assert votacao.save() != null

        params.id = votacao.id

        def model = controller.edit()

        assert model.votacaoInstance == votacao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/votacao/list'

        response.reset()

        populateValidParams(params)
        def votacao = new Votacao(params)

        assert votacao.save() != null

        // test invalid parameters in update
        params.id = votacao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/votacao/edit"
        assert model.votacaoInstance != null

        votacao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/votacao/show/$votacao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        votacao.clearErrors()

        populateValidParams(params)
        params.id = votacao.id
        params.version = -1
        controller.update()

        assert view == "/votacao/edit"
        assert model.votacaoInstance != null
        assert model.votacaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/votacao/list'

        response.reset()

        populateValidParams(params)
        def votacao = new Votacao(params)

        assert votacao.save() != null
        assert Votacao.count() == 1

        params.id = votacao.id

        controller.delete()

        assert Votacao.count() == 0
        assert Votacao.get(votacao.id) == null
        assert response.redirectedUrl == '/votacao/list'
    }
}
