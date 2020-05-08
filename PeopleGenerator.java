import java.util.Random;

public class PeopleGenerator {
    private Random ran;
    private char[] vowels;
    private char[] consonants;
    private String[] syllables = {"cv", "cvc", "vc"};
    private String[] suffixes = {"ов", "ев", "ин"};

    public PeopleGenerator() {
        ran = new Random();
        vowels = "уеыаоэяию".toCharArray();
        consonants = "цкнгзжхфвпрлдчсмтб".toCharArray();
    }

    public String generatePerson() {
        return generateName() + " " + generateSurname();
    }

    private String generateName() {
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < ran.nextInt(2)+2; i++) {
            char[] syl = syllables[ran.nextInt(syllables.length)].toCharArray();
            for (int j = 0; j < syl.length; j++) {
                if (syl[j] == 'c') {
                    syl[j] = consonants[ran.nextInt(consonants.length)];
                } else if (syl[j] == 'v') {
                    syl[j] = vowels[ran.nextInt(vowels.length)];
                }
            }
            if (i == 0)
                syl[0] = Character.toUpperCase(syl[0]);
            name.append(syl);
        }
        return name.toString();
    }
    private String generateSurname() {
        StringBuilder surn = new StringBuilder();
        int gender = ran.nextInt(2);

        for (int i = 0; i < ran.nextInt(1)+3; i++) {
            char[] syl = syllables[ran.nextInt(syllables.length)].toCharArray();
            for (int j = 0; j < syl.length; j++) {
                if (syl[j] == 'c')
                    syl[j] = consonants[ran.nextInt(consonants.length)];
                else if (syl[j] == 'v')
                    syl[j] = vowels[ran.nextInt(vowels.length)];
            }
            if (i == 0)
                syl[0] = Character.toUpperCase(syl[0]);
            surn.append(syl);
        }
        surn.append(suffixes[ran.nextInt(suffixes.length)]);
        if (gender == 1)
            surn.append("a");

        return surn.toString();
    }
}
