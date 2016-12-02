PROJECT TITLE:Chemistry Chatbot
PURPOSE OF PROJECT:Talk with a bot about chemistry
VERSION or DATE:Version 2 - 12/1/2016
HOW TO START THIS PROJECT:use keywords found in the getResponse method
AUTHORS:Ishaque Khan
USER INSTRUCTIONS:type in your response or statement and hit the enter key

METHODS:
getGreeting(): States a greeting to the user, starts off the conversation
getResponse(String statement): Determines an appropriate response based on user input
findKeyword(String statement, String goal, int startPos): Returns an integer that helps break apart user input
transformIWantToStatement(String statement): Turns an "I want to" statement into a question
transformYouMeStatement(String statement): Transforms a "You-me" statement into a question
determineLaw(): Asks the user which gas law they would like to use
avogadroLaw(): Solves for a variable in Avogadro's Law
charlesLaw(): Solves for a variable in Charles' Law
boyleLaw(): Solves for a variable in Boyle's Law
idealLaw(): Solves for a variable in the Ideal Gas Law
getRandomResponse(): If a response can't be created from getResponse(), a random one is generated

PROGRAM HIGHLIGHTS:
Program flows purely based on user input. My code is in short, a giant while loop that carries on a conversation with the user. Basic logic is put to use.

