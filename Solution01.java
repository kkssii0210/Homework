package chap_00;
import java.util.*;
public class Solution01 {

    // 유효기간의 마감 날짜를 계산하는 함수
    //
     private static int[] calculateDate(int year, int month, int term) {
        month += term;
        if (month > 12) {
            year += month / 12;
            month = month % 12;
            if (month == 0) {
                month = 12;
                year--;
            }
        }
        return new int[]{year, month};
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] splitTerm = term.split(" ");
            termMap.put(splitTerm[0], Integer.parseInt(splitTerm[1]));
        }

        String[] todayParts = today.split("\\.");
        int todayYear = Integer.parseInt(todayParts[0]);
        int todayMonth = Integer.parseInt(todayParts[1]);
        int todayDay = Integer.parseInt(todayParts[2]);

        List<Integer> toDiscard = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacyParts = privacies[i].split(" ");
            String[] dateParts = privacyParts[0].split("\\.");

            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            int[] expiryDate = calculateDate(year, month, termMap.get(privacyParts[1]));

            if (expiryDate[0] < todayYear ||
                    (expiryDate[0] == todayYear && expiryDate[1] < todayMonth) ||
                    (expiryDate[0] == todayYear && expiryDate[1] == todayMonth && day <= todayDay)) {
                toDiscard.add(i + 1);
            }
        }

        int[] result = toDiscard.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }


}

