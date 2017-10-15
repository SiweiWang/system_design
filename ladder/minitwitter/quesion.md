# Implement a simple twitter. 

## Support the following method:

1. `postTweet(user_id, tweet_text).` Post a tweet.
1. `getTimeline(user_id).` Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
1. `getNewsFeed(user_id).` Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
1. `follow(from_user_id, to_user_id).` from_user_id followed to_user_id.
1. `unfollow(from_user_id, to_user_id).` from_user_id unfollowed to to_user_id.

## Example

```
postTweet(1, "LintCode is Good!!!")
>> 1
getNewsFeed(1)
>> [1]
getTimeline(1)
>> [1]
follow(2, 1)
getNewsFeed(2)
>> [1]
unfollow(2, 1)
getNewsFeed(2)
>> []
```