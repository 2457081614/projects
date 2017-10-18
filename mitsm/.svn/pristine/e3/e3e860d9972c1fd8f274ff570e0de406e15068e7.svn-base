package com.meession.market.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sam
 */
public class AccessFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(AccessFilter.class);

//	private final IUserService userService = ApplicationContextUtils.getBean(
//			"userService", IUserService.class);
//
//	@SuppressWarnings("unchecked")
//	private final List<Menu> menuList = ApplicationContextUtils.getBean(
//			"menuList", List.class);

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		response.setHeader("X-UA-Compatible", "IE=edge");

		try {
			String path = request.getServletPath();

			// logout
//			if (path.equals("/logout")) {
//				session.invalidate();
//				String logoutUrl = PropertiesUtils.getProperty(PropertyNameConstant.LOGOUT_URL);
//				response.sendRedirect(logoutUrl);
//				return;
//			}

//			String staffCode = (String) session.getAttribute(SessionNameConstant.STAFF_CODE);

//			if (staffCode == null) {
//				String loginType = PropertiesUtils.getProperty(PropertyNameConstant.LOGIN_TYPE);
//
//				if (LoginTypeConstant.SSO.equals(loginType)) {
//					String ntAccount = request.getHeader(HeaderNameConstant.SSO_USER);
//					logger.info("NT Account from http header: {}", ntAccount);
//					if (ntAccount == null) {
//						response.sendError(HttpServletResponse.SC_FORBIDDEN);
//						return;
//					}
//					User user = userService.findUserByNtAccount(ntAccount);
//					if (user != null) {
//						staffCode = user.getStaffCode();
//						session.setAttribute(SessionNameConstant.STAFF_CODE,staffCode);
//					} else {
//						response.sendError(HttpServletResponse.SC_FORBIDDEN);
//						return;
//					}
//				} else if (LoginTypeConstant.BASIC_AUTHENTICATION.equals(loginType)) {
//
//					String authorization = request
//							.getHeader(HeaderNameConstant.Authorization);
//
//					if (authorization == null) {
//						response.setHeader("WWW-authenticate",
//								"Basic realm=\"eds\"");
//						response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//						return;
//					} else {
//						String usernameAndPassword = new String(
//								Base64.decodeBase64(authorization.substring(6)));
//						String username = usernameAndPassword.substring(0,
//								usernameAndPassword.indexOf(":"));
//						User user = userService.findUserByStaffCode(username);
//						if (user != null) {
//							staffCode = user.getStaffCode();
//							session.setAttribute(
//									SessionNameConstant.STAFF_CODE, staffCode);
//						} else {
//							response.setHeader("WWW-authenticate",
//									"Basic realm=\"eds\"");
//							response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//							return;
//						}
//					}
//				}
//			}

//			UserContextHolder.setCurrentStaffCode(staffCode);
//
//			if (!isAllowPath(path)) {
//				response.sendError(HttpServletResponse.SC_FORBIDDEN);
//				return;
//			}

			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			logger.error("Error", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					e.getMessage());
		} finally {
//			UserContextHolder.clear();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	@Override
	public void destroy() {
//		UserContextHolder.clear();
	}

//	private boolean isAllowPath(String path) {
//		User user = UserContextHolder.getCurrentUser();
//		List<Integer> roles = RoleConstant.getRoles(user);
//		for (Menu menu : menuList) {
//			if (menu.getRoles() != null&& path.equals("/pages/" + menu.getUrl())) {
//				boolean allow = false;
//				for (Integer role : menu.getRoles()) {
//					if (roles.contains(role)) {
//						allow = true;
//						break;
//					}
//				}
//				return allow;
//			}
//		}
//		return true;
//	}

}
