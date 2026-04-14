package com.vamshigollapelly.sportsfeedapp.data;

import com.vamshigollapelly.sportsfeedapp.R;
import java.util.Arrays;
import java.util.List;

public class NewsRepository {

    public static List<NewsItem> getAllNews() {
        return Arrays.asList(
                new NewsItem(1, "Man City wins Champions League",
                        "A thrilling final saw Man City claim the title in dramatic fashion after extra time.",
                        "Football", R.drawable.img_sport1),

                new NewsItem(2, "LeBron James scores 50 points",
                        "LeBron delivered a masterclass performance against the Celtics in Game 7.",
                        "Basketball", R.drawable.img_sport2),

                new NewsItem(3, "India beats Australia in Test",
                        "India pulled off a stunning victory chasing 400 in the 5th Test match.",
                        "Cricket", R.drawable.img_sport3),

                new NewsItem(4, "Arsenal top of the Premier League",
                        "Arsenal continue their unbeaten run sitting comfortably at the top of the table.",
                        "Football", R.drawable.img_sport1),

                new NewsItem(5, "NBA Finals Preview",
                        "Everything you need to know ahead of this year's highly anticipated NBA Finals.",
                        "Basketball", R.drawable.img_sport2),

                new NewsItem(6, "T20 World Cup Squad Announced",
                        "The national selectors have named their 15-man squad for the upcoming T20 World Cup.",
                        "Cricket", R.drawable.img_sport3),

                new NewsItem(7, "Ronaldo breaks goal record",
                        "Cristiano Ronaldo has broken the all-time international goal scoring record.",
                        "Football", R.drawable.img_sport1),

                new NewsItem(8, "Stephen Curry 3-point record",
                        "Curry extends his own all-time record with another stunning shooting display.",
                        "Basketball", R.drawable.img_sport2)
        );
    }

    public static List<NewsItem> getFeaturedMatches() {
        return getAllNews().subList(0, 3);
    }
}