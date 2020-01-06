## Redbubble Coding Test

Create a price calculator command-line program. Use any language you like.

### Functional requirements

- Your program should take two command-line arguments:
    - a JSON file representing a cart, and
    - a JSON file representing a list of base prices.
- The cart schema is available at: /cart.schema.json. Some example carts are available at: /cart-4560.json, /cart-9363.json, /cart-9500.json, and /cart-11356.json.
- The base price schema is available at: /base-prices.schema.json. An example of base prices is available at: /base-prices.json.
- You can assume that the options for a product-type are constant.
- For example, if the first record with the product-type 'hoodie' in the list of base prices only has the options 'colour' and 'size', all records with the product-type 'hoodie' in the list of base prices will have the options 'colour' and 'size' and will have no other options.
- Your program should output the total price of the cart in cents followed by a newline character.
- As an aid in testing your program, the name of each example cart file mentioned above includes the expected total price for that cart given the base prices in the example base prices file mentioned above.
- When calculating the total price of the cart, your program should calculate the price for one item as follows: (base_price + round(base_price * artist_markup)) * quantity
- Note that artist_markup is a percentage, so it will need to be divided by 100.
- Your program should handle products with any options, even ones not found in the sample files.
- The time your program takes to calculate a price should be constant with respect to the number of base prices. (In real life, Redbubble currently has more than 1,000 base prices.)
- You need not test that the input files conform with their schemas or handle errors that arise if they don't conform or if they don't go with each other (e.g. if there is no base price for a product type in the cart). We'd all want those tests in a production application, but in this exercise they tend to take time without adding interest.

### Implementation requirements

- You should include thorough automated tests for your code. All of the following applies as much to your tests as to your code.
- You should set up your submission and include any necessary instructions to make it as obvious and convenient as possible for the engineer(s) reviewing your submission to install your program, run it, and run the tests.
- You should write clean, clear, well-factored code which you would be proud to commit at work or to an open-source project.
- Your code should clearly reflect and explain the problem domain.
- You should write code which you can easily extend. We'll ask you to do so later in your interview.

### Submission guidelines

- All of the affirmative requirements above (the ones that say "should") are important. Be sure to address them all and not miss any. If you don't understand any of them, just ask.
- We're not timing you, and you can take as much time as you like, but this test is intended to take you no more than a few hours.
- If you use version control while developing your submission, please include the version history with your submission. This allows us to see the process that you followed while you worked. For example, if you commit to git as you worked, please include the .git directory in the zip file that you send us.
- We'll evaluate you most fairly if only our talent team knows whose submission is being evaluated. Please don't put your name or other identifying information in your submission. If you use version control while developing your submission and include the version history with your submission, please be sure that your version control system doesn't record your identity. For example, if you use git, mask your name and email with
    - git config user.name "Redbubble Applicant"
    - git config user.email me@example.com\
before you commit anything. And do send us a zip file, not just a link to a repository online which would give away your identity.

Copyright © 2016-2020 Redbubble Ltd