Side Project:Automated GIT Commit and Code smell analyzer
=======================================

Server Side:
The webpage using which the user will register for this service using the same username and password as their GIT repository and will bookmark the the ongoing projects that require daily or timely commits.
The user will give input when does the commits need to be made.

Client side:
The client side of the application will keep track of project and store its latest executable file in its cache/folder (every time i run the code which has no errors in it a copy of these files will replace the existing copy in the client cache. Have to think of another way maybe!!). 
Client also has a copy of the latest commit of GIT. 

The client or the server side will have a code smell analyzer which will run during each commit and check if the code being committed has any code smells. 
If so a report of the same will be created and sent to the user.

Update the comments while GIT commit: 
1)Comparing the latest file on GIT and the file to commit (using some online file compare tool). 
2)Record the file name that has changes 
3)List them out in the comments while commit.

I thought building some thing like this would help me a lot in keeping my commits to the GIT timely and also make me aware about the code smells i have in a regular basis so Idon't have to deal with them in the last moment. Also make my coding better that way!! 
