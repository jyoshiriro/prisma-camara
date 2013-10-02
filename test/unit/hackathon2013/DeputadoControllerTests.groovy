package hackathon2013



import org.junit.*
import grails.test.mixin.*

@TestFor(DeputadoController)
@Mock(Deputado)
class DeputadoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/deputado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.deputadoInstanceList.size() == 0
        assert model.deputadoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.deputadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.deputadoInstance != null
        assert view == '/deputado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/deputado/show/1'
        assert controller.flash.message != null
        assert Deputado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/deputado/list'

        populateValidParams(params)
        def deputado = new Deputado(params)

        assert deputado.save() != null

        params.id = deputado.id

        def model = controller.show()

        assert model.deputadoInstance == deputado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/deputado/list'

        populateValidParams(params)
        def deputado = new Deputado(params)

        assert deputado.save() != null

        params.id = deputado.id

        def model = controller.edit()

        assert model.deputadoInstance == deputado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/deputado/list'

        response.reset()

        populateValidParams(params)
        def deputado = new Deputado(params)

        assert deputado.save() != null

        // test invalid parameters in update
        params.id = deputado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/deputado/edit"
        assert model.deputadoInstance != null

        deputado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/deputado/show/$deputado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        deputado.clearErrors()

        populateValidParams(params)
        params.id = deputado.id
        params.version = -1
        controller.update()

        assert view == "/deputado/edit"
        assert model.deputadoInstance != null
        assert model.deputadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/deputado/list'

        response.reset()

        populateValidParams(params)
        def deputado = new Deputado(params)

        assert deputado.save() != null
        assert Deputado.count() == 1

        params.id = deputado.id

        controller.delete()

        assert Deputado.count() == 0
        assert Deputado.get(deputado.id) == null
        assert response.redirectedUrl == '/deputado/list'
    }
}
