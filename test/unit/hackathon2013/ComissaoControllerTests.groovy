package hackathon2013



import org.junit.*
import grails.test.mixin.*

@TestFor(ComissaoController)
@Mock(Comissao)
class ComissaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/comissao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.comissaoInstanceList.size() == 0
        assert model.comissaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.comissaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.comissaoInstance != null
        assert view == '/comissao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/comissao/show/1'
        assert controller.flash.message != null
        assert Comissao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/comissao/list'

        populateValidParams(params)
        def comissao = new Comissao(params)

        assert comissao.save() != null

        params.id = comissao.id

        def model = controller.show()

        assert model.comissaoInstance == comissao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/comissao/list'

        populateValidParams(params)
        def comissao = new Comissao(params)

        assert comissao.save() != null

        params.id = comissao.id

        def model = controller.edit()

        assert model.comissaoInstance == comissao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/comissao/list'

        response.reset()

        populateValidParams(params)
        def comissao = new Comissao(params)

        assert comissao.save() != null

        // test invalid parameters in update
        params.id = comissao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/comissao/edit"
        assert model.comissaoInstance != null

        comissao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/comissao/show/$comissao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        comissao.clearErrors()

        populateValidParams(params)
        params.id = comissao.id
        params.version = -1
        controller.update()

        assert view == "/comissao/edit"
        assert model.comissaoInstance != null
        assert model.comissaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/comissao/list'

        response.reset()

        populateValidParams(params)
        def comissao = new Comissao(params)

        assert comissao.save() != null
        assert Comissao.count() == 1

        params.id = comissao.id

        controller.delete()

        assert Comissao.count() == 0
        assert Comissao.get(comissao.id) == null
        assert response.redirectedUrl == '/comissao/list'
    }
}
