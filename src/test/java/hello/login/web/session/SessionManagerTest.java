package hello.login.web.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.login.domain.member.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

    private SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        //세선 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies()); // mySessionId

        //세션 조희
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}