# cs360_module8
SNHU CS360 Mobile Warehouse Application

1. Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?
   This application is a warehouse inventory application that can be used by any company with a warehouse. It allows users to add, remove, and adjust the quantities of items on shelves in a warehouse.

2. What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
   I needed a login screen to handle user accounts and an inventory screen to handle adding, removing, and adjusting items. The UI I developed prior to developing the application was changed slightly to support a more user-friendly UI design. My original plans didn't take into account the View setup in Android Studio, and while I tried to match the designs with the actual program, I had to make several adjustments.

3. How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?
   I made sure my back end worked before creating the UI. I created a simple UI setup without colors or a user-friendly style and made sure the functions worked properly. Once they did, I edited the UI to make it look nicer and more user-friendly. I think this applies to future projects of mine by making sure the "core" of the program works before the outer layer, being the UI, graphics, effects, and other images.

4. How did you test to ensure your code was functional? Why is this process important and what did it reveal?
   My tests revealed that my database wasn't working properly, nor was switching between two different screens. I was able to fix these, but only because I ran several tests. This process is paramount in application development, as in my opinion, it's better for the developer or testers to find issues like that than it is for the customer.

5. Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?
   In order to get my database working properly, I had to rearrange how I handled storing inventory items. Instead of having a separate screen for adding an item, I had this function inside the "grid" screen. This allowed for items to be added, removed, and adjusted without switching screens. I don't know why that was causing the issue, but adding all functions into one screen did the trick.

6. In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?
   I was impressed with my ability to get the user login system working. I implemented that relatively early on in the project. At first, I thought the database had to come from an external source such as XAMPP and MySql, but once I realized Android Studio had its own built-in database (or so I read), I readjusted my code and found that my attempts at creating a login system was successful.
