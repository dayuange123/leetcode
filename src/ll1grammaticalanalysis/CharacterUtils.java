package ll1grammaticalanalysis;

import java.util.List;

public class CharacterUtils {
    public static Character getNonTerminator(List<Character> characters) {
        Character character = 'A';
        for (int i = 0; i < 26; i++) {
            char aa = (char) ('A' + i);
            if (!characters.contains(aa)) {
                characters.add(aa);
                return aa;
            }
        }
        throw new IllegalArgumentException("G了————26个大小字母用完了，文法太复杂");
    }
}