package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest () {
        //given 세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        //when
        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //requet 에 담긴 쿠키로 세션 조회
        Object result = sessionManager.getSession(request);

        //then
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.exprie(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }

}