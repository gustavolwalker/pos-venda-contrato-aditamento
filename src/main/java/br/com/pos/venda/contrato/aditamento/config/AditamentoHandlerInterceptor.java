package br.com.pos.venda.contrato.aditamento.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.pos.venda.contrato.aditamento.exception.AditamentoException;

@Component
public class AditamentoHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String chavePosVenda = request.getHeader("itau-pos-venda-teste");

		if (chavePosVenda == null || !chavePosVenda.matches("[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")) {
			throw new AditamentoException("'itau-pos-venda-teste' inválidado.");
		}

		return true;

	}
}
