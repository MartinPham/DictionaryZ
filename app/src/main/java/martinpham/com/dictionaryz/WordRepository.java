package martinpham.com.dictionaryz;

import java.util.ArrayList;

public class WordRepository {
    public static ArrayList<Word> findByName(String name)
    {
        ArrayList<Word> words = new ArrayList<Word>();

        for(int i = 0; i < 3; i++)
        {
            Word word = new Word();
            word.setName("test " + String.valueOf(i));
            word.setDescription("test is a test");
            word.setCardImageUrl("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");
            word.setBackgroundImageUrl("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");

            words.add(word);
        }


        return words;
    }

    public static ArrayList<Word> featuredList()
    {
        ArrayList<Word> words = new ArrayList<Word>();

        Word word = new Word();
        word.setName("test");
        word.setDescription("test is a test");
        word.setCardImageUrl("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");
        word.setBackgroundImageUrl("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");

        words.add(word);

        return words;
    }
}
