## dump edn
```
(require '[inspect.core])
(dump-html data) ; will dump data into dump.html
```

screenshots 

https://imgur.com/l1JbgEB
https://imgur.com/HVVvU3k



## clip
```
(require '[inspect.clip :refer [clip])

(clip (f arg)) ; copy the output to current clip buffer
```
