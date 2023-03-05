package io.dev.deneb;

import static io.dev.deneb.PasswordStrength.*;

public class PasswordPolicy {

  public PasswordStrength analyze(String password) {
    if (password == null || password.isBlank())
      throw new IllegalArgumentException("비밀번호 입력값이 없습니다");

    int correctCount = calculateCorrectCount(password);

    if (correctCount == 3) return STRONG;
    if (correctCount == 2) return NORMAL;

    return WEAK;

  }

  private static int calculateCorrectCount(String password) {
    int correctCount = 0;
    if (validLength(password)) correctCount++;
    if (containUpperCase(password)) correctCount++;
    if (containNumber(password)) correctCount++;
    return correctCount;
  }

  private static boolean validLength(String password) {
    return password.length() >= 8;
  }

  private static boolean containNumber(String password) {
    for (char ch : password.toCharArray()) {
      if (ch >= '0' && ch <= '9') {
        return true;
      }
    }
    return false;
  }

  private static boolean containUpperCase(String password) {
    for (char ch : password.toCharArray()) {
      if (ch >= 'A' && ch <= 'Z') {
        return true;
      }
    }
    return false;
  }

}
