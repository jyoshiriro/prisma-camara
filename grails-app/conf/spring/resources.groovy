
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

beans = {
	
	redirectFailureHandlerPrismaCamara(SimpleUrlAuthenticationFailureHandler) {
		defaultFailureUrl = '/'
	}
	
	redirectSuccessHandlerPrismaCamara(SimpleUrlAuthenticationSuccessHandler) {
		defaultTargetUrl = '/painel'
	}
	
}
