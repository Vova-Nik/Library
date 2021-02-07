Test task
Create a RESTful Spring Boot application that will represent a simple "Library" system with two main entities - User and Book. A user can have many books and a book can only belong to 1 user. A book can be free (allowed to be taken by other users) or taken (not allowed to be taken by other users)

Rest controllers should allow to:
- CRUD user
- CRUD book
- Take a book (a book will become taken by a certain user)
- Return book (a book will be free for another user to take)
- (Optional) Get information about the users and the books they currently have

No UI is needed.
Use any ORM you like

******************************************************************************************
UI - just for testing 	src\main\resources\static\index.html
							localhost:8080/index

- CRUD user     com.nikollenko.library.model.Client.java 
                com.nikollenko.library.services.ClientServiceImpl.java 
                com.nikollenko.library.rest.ClientController.java 
						@PostMapping("/user/add")		
						@PutMapping("/user/update")		
						@DeleteMapping("/user/remove")
						@DeleteMapping("/user/remAll")
						@GetMapping("/user/getbyid")
						@GetMapping("/user/getall")

- CRUD book    com\nikollenko\library\model\Book.java 
               com.nikollenko.library.services.BookServiceImpl.java 
               com.nikollenko.library.rest.BookController.java 
                      @PostMapping("/book/add")
                      @PutMapping("/book/update")
					  @GetMapping("/book/getbyid")
					  @GetMapping("/book/getall")
					  @DeleteMapping("/book/remove")
					  @DeleteMapping("/book/remall")
      
                      
 - take book      com.nikollenko.library.rest.ActionController.java 
                      @PostMapping("/action/take")
                      
 - return book    com.nikollenko.library.rest.ActionController.java 
                      @PostMapping("/action/return")
					  