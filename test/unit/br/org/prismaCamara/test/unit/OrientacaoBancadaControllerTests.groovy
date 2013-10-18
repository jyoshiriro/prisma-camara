package br.org.prismaCamara.test.unit



import org.junit.*
import grails.test.mixin.*

@TestFor(OrientacaoBancadaController)
@Mock(OrientacaoBancada)
class OrientacaoBancadaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/orientacaoBancada/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.orientacaoBancadaInstanceList.size() == 0
        assert model.orientacaoBancadaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.orientacaoBancadaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.orientacaoBancadaInstance != null
        assert view == '/orientacaoBancada/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/orientacaoBancada/show/1'
        assert controller.flash.message != null
        assert OrientacaoBancada.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/orientacaoBancada/list'

        populateValidParams(params)
        def orientacaoBancada = new OrientacaoBancada(params)

        assert orientacaoBancada.save() != null

        params.id = orientacaoBancada.id

        def model = controller.show()

        assert model.orientacaoBancadaInstance == orientacaoBancada
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/orientacaoBancada/list'

        populateValidParams(params)
        def orientacaoBancada = new OrientacaoBancada(params)

        assert orientacaoBancada.save() != null

        params.id = orientacaoBancada.id

        def model = controller.edit()

        assert model.orientacaoBancadaInstance == orientacaoBancada
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/orientacaoBancada/list'

        response.reset()

        populateValidParams(params)
        def orientacaoBancada = new OrientacaoBancada(params)

        assert orientacaoBancada.save() != null

        // test invalid parameters in update
        params.id = orientacaoBancada.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/orientacaoBancada/edit"
        assert model.orientacaoBancadaInstance != null

        orientacaoBancada.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/orientacaoBancada/show/$orientacaoBancada.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        orientacaoBancada.clearErrors()

        populateValidParams(params)
        params.id = orientacaoBancada.id
        params.version = -1
        controller.update()

        assert view == "/orientacaoBancada/edit"
        assert model.orientacaoBancadaInstance != null
        assert model.orientacaoBancadaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/orientacaoBancada/list'

        response.reset()

        populateValidParams(params)
        def orientacaoBancada = new OrientacaoBancada(params)

        assert orientacaoBancada.save() != null
        assert OrientacaoBancada.count() == 1

        params.id = orientacaoBancada.id

        controller.delete()

        assert OrientacaoBancada.count() == 0
        assert OrientacaoBancada.get(orientacaoBancada.id) == null
        assert response.redirectedUrl == '/orientacaoBancada/list'
    }
}
