import java.util.*;

/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 */


public class MiniTwitter {
    class Node {
        public int order;
        public Tweet tweet;
        public Node(int o, Tweet t) {
            this.order = o;
            this.tweet = t
        }
    }

    class SortbyOrder implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Node n1 = (Node) obj1;
            Node n2 = (Node) obj2;

            // smaller the order, higher the rank
            if (n1.order < n2.order){
                return 1;
            }else{
                return -1;
            }
        }
    }

    // 1 person to have set of friends
    private Map<Integer, Set<Integer>> fridens;
    private Map<Integer, List<Node>> users_tweets;
    private int order;

    public List<Node> getLastTen(List<Node> tmp) {
        int offset = tmp.size() < 10? tmp.size():10;
        return tmp.subList(tmp.size() - offset, tmp.size());
    }

    public List<Node> getFirstTen(List<Node> tmp) {
        int offset = tmp.size() < 10? tmp.size():10;
        return tmp.subList(0, offset);
    }

    public MiniTwitter() {
        this.fridens = new HashMap<Integer, Set<Integer>>();
        this.users_tweets = new  HashMap<Integer, List<Node>>();
        this.order = 0;
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet tweet = Tweet.create(user_id, tweet_text);

        // lazy init
        if (!users_tweets.containsKey(user_id)){
            users_tweets.put(user_id, new ArrayList<Node>());
        }

        order += 1;
        users_tweets.get(user_id).add(new Node(order, tweet));
        return tweet;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        // write your code here

        // Get tweets by oneself
        List<Node> tmp = new ArrayList<Node>();
        if (users_tweets.containsKey(user_id)){
            tmp.addAll(getLastTen(users_tweets.get(user_id)));
        }

        // Get tweets by friends
        if (fridens.containsKey(user_id)){
            for (Integer user: fridens.get(user_id)){
                if (users_tweets.containsKey(user)){
                    tmp.addAll(getLastTen(users_tweets.get(user)));
                }
            }
        }

        Collections.sort(tmp, new SortbyOrder());

        List <Tweet> tl = new ArrayList<Tweet>();
        tmp = getFirstTen(tmp);
        for (Node node: tmp){
            tl.add(node.tweet);
        }
        return rt;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        // write your code here

        // Get tweets by oneself
        List<Node> tmp = new ArrayList<Node>();
        if (users_tweets.containsKey(user_id)){
            tmp.addAll(getLastTen(users_tweets.get(user_id)));
        }

        Collections.sort(tmp, new SortbyOrder());
        List <Tweet> tl = new ArrayList<Tweet>();
        tmp = getFirstTen(tmp);
        for (Node node: tmp){
            tl.add(node.tweet);
        }
        return rt;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        if (!fridens.containsKey(from_user_id)){
            fridens.put(from_user_id, new HashSetM<Integer>());
        }
        fridens.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        // write your code here
        if (fridens.containsKey(from_user_id)){
            fridens.get(from_user_id).remove(to_user_id);
        }
    }
}