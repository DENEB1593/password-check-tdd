package io.dev.deneb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PasswordCheckTests {

  @DisplayName("빈값 혹은 null인 경우 예외발생")
  @Test
  void shouldThrowExceptionWhenNullOrEmpty() {
    assertThatThrownBy(() -> { new PasswordPolicy().analyze(null);})
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("비밀번호 입력값이 없습니다");
  }

  @DisplayName("모든 규칙을 충족하면 강함")
  @Test
  void compliedAllRules() {
    assertPasswordStrength("password1A", PasswordStrength.STRONG);
    assertPasswordStrength("dnmccba1!BC", PasswordStrength.STRONG);
  }


  @DisplayName("길이가 8미만인 경우 강함이 아닌 다른 결과")
  @Test
  void lengthUnder8Test() {
    assertPasswordStrength("12345A7", PasswordStrength.NORMAL);
  }

  @DisplayName("대문자가 없는 경우 강함이 아닌 다른 결과")
  @Test
  void noUpperCase() {
    assertPasswordStrength("sdasd123s", PasswordStrength.NORMAL);
  }

  @DisplayName("숫자가 없는 경우, 강함이 아닌 다른 결과")
  @Test
  void uppercaseWithLength8() {
    assertPasswordStrength("sdfgABCs", PasswordStrength.NORMAL);
  }

  @DisplayName("대문자만 충족하는 경우")
  @Test
  void onlyContainUppercase() {
    assertPasswordStrength("Aasbvx", PasswordStrength.WEAK);
  }

  @DisplayName("길이만 충족하는 경우")
  @Test
  void onlyValidLength() {
    assertPasswordStrength("swqxcvvds", PasswordStrength.WEAK);
  }


  @DisplayName("숫자만 충족하는 경우")
  @Test
  void onlyContainNumber() {
    assertPasswordStrength("123445", PasswordStrength.WEAK);
  }

  private static void assertPasswordStrength(String password, PasswordStrength expected) {
    PasswordStrength result = new PasswordPolicy().analyze(password);
    assertThat(result).isEqualTo(expected);
  }
}
