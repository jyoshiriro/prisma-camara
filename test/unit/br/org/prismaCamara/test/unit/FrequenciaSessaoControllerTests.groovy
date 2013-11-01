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

@TestFor(FrequenciaSessaoController)
@Mock(FrequenciaSessao)
class FrequenciaSessaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/frequenciaSessao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.frequenciaSessaoInstanceList.size() == 0
        assert model.frequenciaSessaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.frequenciaSessaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.frequenciaSessaoInstance != null
        assert view == '/frequenciaSessao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/frequenciaSessao/show/1'
        assert controller.flash.message != null
        assert FrequenciaSessao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaSessao/list'

        populateValidParams(params)
        def frequenciaSessao = new FrequenciaSessao(params)

        assert frequenciaSessao.save() != null

        params.id = frequenciaSessao.id

        def model = controller.show()

        assert model.frequenciaSessaoInstance == frequenciaSessao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaSessao/list'

        populateValidParams(params)
        def frequenciaSessao = new FrequenciaSessao(params)

        assert frequenciaSessao.save() != null

        params.id = frequenciaSessao.id

        def model = controller.edit()

        assert model.frequenciaSessaoInstance == frequenciaSessao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaSessao/list'

        response.reset()

        populateValidParams(params)
        def frequenciaSessao = new FrequenciaSessao(params)

        assert frequenciaSessao.save() != null

        // test invalid parameters in update
        params.id = frequenciaSessao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/frequenciaSessao/edit"
        assert model.frequenciaSessaoInstance != null

        frequenciaSessao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/frequenciaSessao/show/$frequenciaSessao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        frequenciaSessao.clearErrors()

        populateValidParams(params)
        params.id = frequenciaSessao.id
        params.version = -1
        controller.update()

        assert view == "/frequenciaSessao/edit"
        assert model.frequenciaSessaoInstance != null
        assert model.frequenciaSessaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaSessao/list'

        response.reset()

        populateValidParams(params)
        def frequenciaSessao = new FrequenciaSessao(params)

        assert frequenciaSessao.save() != null
        assert FrequenciaSessao.count() == 1

        params.id = frequenciaSessao.id

        controller.delete()

        assert FrequenciaSessao.count() == 0
        assert FrequenciaSessao.get(frequenciaSessao.id) == null
        assert response.redirectedUrl == '/frequenciaSessao/list'
    }
}
