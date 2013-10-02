package hackathon2013



import org.junit.*
import grails.test.mixin.*

@TestFor(VotoController)
@Mock(Voto)
class VotoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/voto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.votoInstanceList.size() == 0
        assert model.votoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.votoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.votoInstance != null
        assert view == '/voto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/voto/show/1'
        assert controller.flash.message != null
        assert Voto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/voto/list'

        populateValidParams(params)
        def voto = new Voto(params)

        assert voto.save() != null

        params.id = voto.id

        def model = controller.show()

        assert model.votoInstance == voto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/voto/list'

        populateValidParams(params)
        def voto = new Voto(params)

        assert voto.save() != null

        params.id = voto.id

        def model = controller.edit()

        assert model.votoInstance == voto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/voto/list'

        response.reset()

        populateValidParams(params)
        def voto = new Voto(params)

        assert voto.save() != null

        // test invalid parameters in update
        params.id = voto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/voto/edit"
        assert model.votoInstance != null

        voto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/voto/show/$voto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        voto.clearErrors()

        populateValidParams(params)
        params.id = voto.id
        params.version = -1
        controller.update()

        assert view == "/voto/edit"
        assert model.votoInstance != null
        assert model.votoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/voto/list'

        response.reset()

        populateValidParams(params)
        def voto = new Voto(params)

        assert voto.save() != null
        assert Voto.count() == 1

        params.id = voto.id

        controller.delete()

        assert Voto.count() == 0
        assert Voto.get(voto.id) == null
        assert response.redirectedUrl == '/voto/list'
    }
}
