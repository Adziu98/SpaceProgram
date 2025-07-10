Hello Code reviewers!
First things first about this task. I am not sure am I was able to use lombok and some test libraries for this solution, but I did :D Anyway change to build object in old fashion way would not be a problem.
I did not write unit test for method which prints summary for space program cause lack of time, it would take time to do it for me in that case. I could use AI to make it fast, but i prefer to do it by myself.

I have used AI(LLM) to make an draft of solution for this task, to get a look how to do it, later I have refactored code to make it more readeable. Ofc sometimes when i got trouble (mainly with printing summary) i asked a AI or google few times some basic stuff
e.g. why logging does not work. Most of the time i was using System.our.println to check how api is working, but at the last few minutes Ive decided to switch to log.info, error and throw some exception. I could also not use generic IllegalArgumentException but define one
bymyself.

Other improvemnts that could be made, is to create util class with static strings. not big change but makes code more readable. Ive tried to make code simply and readable with logging. Tested corner cases. Ofc there is room for improvment.
Thats all thanks :)
