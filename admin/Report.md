# [Team Name] Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

*Here are some tips to write a good report:*

* *Try to summarise and list the `bullet points` of your project as many as possible rather than give long, tedious paragraphs that mix up everything together.*

* *Try to create `diagrams` instead of text descriptions, which are more straightforward and explanatory.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report.*

*Please remove the instructions or examples in `italic` in your final report.*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Conflict Resolution Protocol](#conflict-resolution-protocol)
4. [Application Description](#application-description)
5. [Application UML](#application-uml)
6. [Application Design and Decisions](#application-design-and-decisions)
7. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
8. [Testing Summary](#testing-summary)
9. [Implemented Features](#implemented-features)
10. [Team Meetings](#team-meetings)

## Team Members and Roles

| UID | Name | Role |
| :--- | :----: | ---: |
| [u7385305] | [Zhaoyu Cao] | [Developer(Test)] |
| [u7341252] | [Enze Peng] | [Developer(Database)] |
| [u7370885] | [Yonghao Deng] | [Developer(Function),PM] |
| [u7354208] | [Zihan Meng] | [Developer(Front-End design)] |

## Summary of Individual Contributions

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*


*u7385305, Zhaoyu Cao, I contribute 33% of the code. Here are my contributions:*
* LoginActivity.class
* ContentActivity.class
* UserProfileActivity.class
* Report Writing: 
* Slide preparation: 

*u7341252, Enze Peng, I contribute 33% of the code. Here are my contributions:*
* MainActivity.class
* MyApplication.class
* Post.class
* PostActivity.class
* Report Writing: 
* Slide preparation: 

*u7370885, Yonghao Deng, I contribute 33% of the code. Here are my contributions:*
* MyPostActivity.class: use the HashMap data structures
* SearchActivity.class
* RegisterActivity.class: Set an OnClickListener to the button of register.
* iterater.class: use the iterater design patterns
* container.class
* Report Writing: 
* Slide preparation: 

*u7354208, Zihan Meng, I contribute 33% of the code. Here are my contributions:*
* activity_content.xml
* activity_login.xml
* activity_main.xml
* activity_my_post.xml
* activity_neraby.xml
* activity_post.xml
* activity_register.xml
* activity_search.xml
* activity_user_profile.xml
* list_item_layout.xml
* Report Writing: 
* Slide preparation: 

*[Code Design. What design patterns, data structures, did the involved member propose?]*

*[UI Design. Specify what design did the involved member propose? What tools were used for the design?]*

*[Report Writing. Which part of the report did the involved member write?]*

*[Slide preparation. Were you responsible for the slides?]*

*[Miscellaneous contributions. You are welcome to provide anything that you consider as a contribution to the project or team.]*

## Conflict Resolution Protocol
When our group encounters conflict:

1. Conduct an emergency meeting to discuss the causes of the problem and document them
2.  -  In the initial discussion if we can unify the ideas of each member of the group, then we will directly make changes in the code.
    -  In the initial discussion if we can not unify the ideas of each member of the group, we will review online materials or ask our tutors to    finalize our uniform version.

## Application Description

Treehole is a social media application for college students that provides a platform for each college student to share their daily life or thoughts. Users can also view posts sent by others and like or comment on them after viewing them for the purpose of making friends and communicating with them, which helps students' mental health development.

**Application Use Cases and or Examples**
*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here is a pet training application example*

*Lisa is a student from China who is in his first year at the Australian National University and this is his first year in Australia.*
1. *After living away from Lisa's parents in Australia for three months for the first time, she missed her parents very much*
2. *Lisa posted a post about her feelings about coming to Australia for the past three months and how much she misses her home and family.*
3. *Carmelo saw Lisa's post, he first liked Lisa's post, then wrote a lot of comforting sentences in the comment section, and promised to go out with Lisa one weekend afternoon*

*Targets Users: Students*

* *Users can send their daily life to share with others.*
* *Users can send their thoughts at any time, such as their love of food, thoughts of home, appreciation of others, etc.*
* *Users can send some comments and opinions about the school.*

*Target Users: Teachers,Faculty and Staff*

* *Users can view suggestions for the school and make improvements*
* *Users can monitor students' psychological status and provide online counseling for students with psychological problems*

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

## Application UML

![ClassDiagramExample](./images/ClassDiagramExample.png)
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

## Application Design and Decisions

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design. Here is an example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*

   * *Objective: It is used for storing xxxx for xxx feature.*

   * *Locations: line xxx in XXX.java, ..., etc.*

   * *Reasons:*

     * *It is more efficient than Arraylist for insertion with a time complexity O(1)*

     * *We don't need to access the item by index for this feature*

2. ...

3. ...

**Data Structures**

*[What data structures did your team utilise? Where and why?]*

**Design Patterns**

*[What design patterns did your team utilise? Where and why?]*

**Grammar(s)**

<br> *Production Rules* <br>
\<Non-Terminal> ::= \<some output>
<br>
\<Non-Terminal> ::= \<some output>

*[How do you design the grammar? What are the advantages of your designs?]*

*If there are several grammars, list them all under this section and what they relate to.*

**Tokenizer and Parsers**

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*

**Surpise Item**

*[If you implement the surprise item, explain how your solution addresses the surprise task. What decisions do your team make in addressing the problem?]*

**Other**

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*

*Here is an example:*

1. *Bug 1:*

- *A space bar (' ') in the sign in email will crash the application.*
- ...

2. *Bug 2:*
3. ...

*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

## Testing Summary

*[What features have you tested? What is your testing coverage?]*

*Here is an example:*

*Number of test cases: ...*

*Code coverage: ...*

*Types of tests created: ...*

*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

## Implemented Features

*[What features have you implemented?]*

*Here is an example:*

*Privacy*
1. *Featue 1: Users may ... . (easy)*
2. *Feature 2: A user must ... . (easy)*
3. *Feature 3: A user can only ... . (medium)*

*Firebase Integration*
1. *Feature 1: Use Firebase to implement ... . (easy)*
2. *Feature 2: Use Firebase to persist ... .(medium)*

*List all features you have completed in their separate categories with their difficulty classification. If they are features that are suggested and approved, please state this somewhere as well.*

## Team Meetings

*Here is an example:*

- *[Team Meeting 1](./MeetingTemplate.md)*
- ...

*Either write your meeting minutes here or link to documents that contain them. There must be at least 4 team meetings. Note that you must commit your minute meetings shortly after your meeting has taken place (e.g., within 24h), otherwise your meeting minute will not be accepted.*
