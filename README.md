# StudyFi

## Team members:

| Name | Student ID |
|-|-|
| Jordan Kwek/Koroski | S10205052 |
| Syed Sharuk | S10194023 |
| Jun An | S10206062 |



## Description of app:
Productive study app that allows users to be less distracted and study more efficiently.
With all the integrated features found inside the app, users will not have any excuses
to go back to the homescreen where they might get distracted easily and proceed to do 
something else. Users can take down notes, keep track of their daily task, message other 
user who are using StudyFi, listen to radio and songs, keep track of how long they have 
been studying for, search for information using our inapp google search bar and a button
to link to photomath for users who need help with mathematics

## Features in app and how to use them:
- To-do list 
> Allows user to take down daily task. With the add button, they can add new task. When they press and hold on the task, they can delete it. If they complete the task and tick the checkbox, they are given an option to delete the task. By click on the title of the task, they will be able to edit the task. After checking the checkbox or editting the title, the date below the title of the task will be updated to the current date when they last editted the task

- Note taking 

- Countdown/countup timer
> Allows user to either set a time to count down from in minutes or to start a stopwatch that counts up from 0. Both of these features are on the same timer page, setting a time in minutes and clicking the confirm button will begin to start the countdown. Clicking on the pause button any time during the countdown will pause the timer, and resume the timer once it is clicked again. Clicking on the pause button while there is no countdown active will start the stopwatch instead, with the timer starting to count up from 00 : 00. The reset button at the bottom of the page will reset any countdown or stopwatch that is currently active and revert the timer back to 0.

- Radio (online) / Songs (offline)
> Allows user to select online radio or offline songs to listen to. Upon selecting a song, there will be 2 buttons for them to either start or stop the music.

- Direct link to photomath
-
- Messaging function
> Before the user gets to use the messaging function, they have to either login or register an account. During the registration, they have to make sure that their passwords are more than 6 characters long, password and confirm password must match, and email must be in the correct format. After registering, they will be able to message all the users who registered in the studyFi app as well. They enter their text message in the text box and click on the send button on the right side of it to send the message

- Inapp google search bar

- Motivational Quote
> A randomly selected Motivational Quote taken from an API will be occasionally sent out to the user from the app around a four-hour interval.

## Roles and contributions of each members

**Jordan Kwek**

**Role**

 - UI designer
 - Idea generator
 - Advice giver
 - Function tester
 - Radio link sourcer
 - Songs sourcer

**Contributions**

 - To-do list recyclerview
 - To do list sql lite database (edit, add, delete)
 - Custom dialogs for adding and editing tasks
 - Radio recyclerview
 - Online / Offline mediaplayer
 - Storing radio data on firebase
 - Made timer, homepage, todo list, radio page responsive
 - Messaging login page
 - Messaging registration page
 - Messaging user recyclerview
 - Messaging chat recyclerview 
 - Storing of messaging on firebase
 
**Syed Sharuk**

**Role**

 - UI designer
 - Idea generator
 - Advice giver
 - Function tester
 - study note creator

**Contributions**

- Created home page 
- study note app ( Edit, create and delete)
- permanent storage
- implemenet google bar
- helped with powerpoint slides
- helped to brainstorm ideas
- added to link to open photomath

**Ho Jun An**

**Role**

- UI designer
- Idea generator
- Advice giver
- Function tester
- Timer creator
- Motivational Quote creator

**Contributions**

- Helped with the powerpoint slides
- Helped to brainstorm ideas
- Created Stopwatch to allow the user to set specific time
- Created a timer system to allow the user to keep track of time
- Added Motivational Quotes taken from an API that will occasionally be sent through a Notification

## Screenshots of app with user guides

## Diagram to show functions in app

[![Power-Point-Slide-Show-Presentation1-6-8-2021-1-48-55-AM.png](https://i.postimg.cc/Zq1nmGCG/Power-Point-Slide-Show-Presentation1-6-8-2021-1-48-55-AM.png)](https://postimg.cc/cKB0M57h)

## Homescreen

[![homescreen.jpg](https://i.postimg.cc/Vv7s39pv/homescreen.jpg)](https://postimg.cc/ctnN33sG)

## To-do List

When user clicks on the to-do list button on the homescreen

[![todolist.jpg](https://i.postimg.cc/FHvmDB7p/todolist.jpg)](https://postimg.cc/2V0P5TdL)

if user presses on the add button at the bottom left of the screen, user can choose to either input a task and add the task which will be displayed on the list of tasks or press on the cancel button.

[![addtask.jpg](https://i.postimg.cc/cCYycRWP/addtask.jpg)](https://postimg.cc/GTc5LsHj)

if user presses on the to-do task title, user can update their task or press on the cancel button

[![edittask.jpg](https://i.postimg.cc/MK28pFbw/edittask.jpg)](https://postimg.cc/t75crDMv)

if user checks the task, user will be prompted to delete the task. User can choose to either delete or cancel to keep the task

[![completetask.jpg](https://i.postimg.cc/zftrDDp2/completetask.jpg)](https://postimg.cc/sBh8wrk5)

if user press and holds the task, user will be prompted to delete the task. User can choose to delete or keep the task

[![deletetask.jpg](https://i.postimg.cc/y874F3br/deletetask.jpg)](https://postimg.cc/dLNxF3v8)

## Radio

When user clicks on the radio button on the homescreen. Songs that can be listened to offline
will be labelled offline and radio stations will be online as they require internet connection

[![selectradio.jpg](https://i.postimg.cc/W4fBLWbQ/selectradio.jpg)](https://postimg.cc/Y4YnQxF3)

when user selects a radio/song to listen to. User can either play or stop the music

[![radio.jpg](https://i.postimg.cc/d3GpCJmY/radio.jpg)](https://postimg.cc/645MJJmj)

## Messaging

When user clicks on the messaging button on the homescreen. If user has an existing account,
they can login. If user does not have an account, they can click on the "Sign up" bolded text below 
the sign in button.

[![login.jpg](https://i.postimg.cc/h4mH60F5/login.jpg)](https://postimg.cc/Z0T7yp58)

If user decides to sign up for a new account, they will have to enter their name, email and
passwords. Their email must match the email pattern and passwords must be more than 6 characters long. Password must also match the confirm password. However, if they already have an account, they can click on the "Sign in" bolded text below
the register button

[![register.jpg](https://i.postimg.cc/5NMZVFbN/register.jpg)](https://postimg.cc/grNMyjxf)

After they register or login, they will be able to see the other users using StudyFi app. They will 
be able to chat freely with each and every one of them.

[![messaginguser.jpg](https://i.postimg.cc/zGz6LZys/messaginguser.jpg)](https://postimg.cc/xcZt7Zy5)


They can enter their messages at the bottom of the screen and press on the button on the right side
of the input text box to send their messages.

[![chat.jpg](https://i.postimg.cc/pLs7zG8p/chat.jpg)](https://postimg.cc/SnzDpGgh)

home page of the study note app

[![Study-Home.jpg](https://i.postimg.cc/76S4wZPR/Study-Home.jpg)](https://postimg.cc/dkQp9vyR)

click add in the action bar to add new notes

[![StudyAdd.jpg](https://i.postimg.cc/T3SvbDpS/studyAdd.jpg)](https://postimg.cc/HrQNDj04)

holding down the notes app will let you delete it.

[![Study-Delete.jpg](https://i.postimg.cc/3RQMSQjF/study-Delete.jpg)](https://postimg.cc/Mc5rH4Fn)

click on the note to expand it and view larger texts and edit it

[![StudyFull.jpg](https://i.postimg.cc/9MhSPtVx/study-full.jpg)](https://postimg.cc/3ytnYGkp)

search bar

[![search.jpg](https://i.postimg.cc/tCwBttNh/search.jpg)](https://postimg.cc/fJ77MdFL)

google search

[![searchBar.jpg](https://i.postimg.cc/ZnqwjwQL/seachBar.jpg)](https://postimg.cc/fJ77MdFL)

timer page

[![timerpage.png](https://i.postimg.cc/jd81DWv8/timerpage.png)](https://postimg.cc/jL7c9SpN)

set a time at set timer

[![settimer.png](https://i.postimg.cc/KzSGCpMg/settimer.png)](https://postimg.cc/3y1QvB9K)

click confirm to start counting down

[![timercountdown.png](https://i.postimg.cc/x1MFtsnZ/timercountdown.png)](https://postimg.cc/Yv23Sz4N)

click pause button while countdown to pause

[![timerpause.png](https://i.postimg.cc/kXB56SqG/timerpause.png)](https://postimg.cc/LnFSGnqF)

toast when timer runs out

[![timerfinish.png](https://i.postimg.cc/Hn8fHT1Y/timerfinish.png)](https://postimg.cc/y3Bfy4Nt)

click pause button while timer is inactive to start counting up

[![timercountup.png](https://i.postimg.cc/L8vRx6Hw/timercountup.png)](https://postimg.cc/hXJHjnQ1)

click reset button to revert everything to normal

[![timerreset.png](https://i.postimg.cc/y69HwT1F/timerreset.png)](https://postimg.cc/K4ZwTBgz)

## User Diagram

User diagram for messaging
[![Screenshot-2021-08-02-235228.png](https://i.postimg.cc/t4mvd86K/Screenshot-2021-08-02-235228.png)](https://postimg.cc/K1B5xqJf)

## References

Music used in the app - https://www.bensound.com
