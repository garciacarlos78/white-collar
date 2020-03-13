# IT Academy "White collar" exercise
Run the project as Spring Boot App in your favourite IDE and access http://localhost:8080 in your favourite web browser.  
Lombok plugin is necessary to be installed in the IDE.

# Considerations
  - We add an id to each store because the contract asks for it implicitly in the API functionalities (POST, GET, DELETE).
  - We are not asked for an id for the paintings, so the painting name will be its PK.
  - GET /shops/
    - We are only asked for name and capacity, so we list just those fields (not id)
  - POST /shops/{ID}/pictures
    - The contract asks us to create a painting introducing just painting and painter name, so we assign the painting a random price. The entry date is the moment in which we make the post request.
    
# Doubts
## Painting repository, is it really necessary?
I think it would have been possible not having a painting repository (we can simply have a List<Painting> in each store). Problem: we could have then repeated paintings, the same painting in two different stores. I think then it forces to have also a painting repository.

## LoadDatabase.java
### application.properties
In order to be able to preload data, I've had to add the following line in *application.properties*:
`spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true`  
Is it the right way or is it just a dirty workaround? Is there a more elegant solution?  
Error obtained without that property:  
`java.lang.IllegalStateException: Failed to execute CommandLineRunner
[...]`    
`Caused by: org.hibernate.LazyInitializationException: could not initialize proxy [com.cgrdev.whitecollar.domain.data.Store#1] - no Session
	at org.hibernate.proxy.AbstractLazyInitializer.initialize(AbstractLazyInitializer.java:170) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]...`

### LoadData: adding paintings to the stores only possible in Store creation.

The only way I've had to preload paintings into the stores has been to create a `List\<Painting>` and add them to the stores at store creation time.  
  `[Create List<Painting>]`  
  `storeRepository.save(new Store("Store 4", 6, List<Painting>));`
  
If I create the store before, and then try adding paintings one by one, the paintings are not added.  
  `storeRepository.save(new Store("Store 4", 6));`  
  `storeRepository.getOne(4L).getPaintings().add(paintingRepository.save(new Painting("Painter 1", "Painting name 5", 50, new Date())));`

If I insert the paintings in the repository before, then create the store, and then try adding paintings one by one from the painting repository, the paintings are not added.  
  `[Add paintings to Painting repository]`  
  `storeRepository.save(new Store("Store 4", 6));`  
  `storeRepository.getOne(4L).getPaintings().add(paintingRepository.getOne(<idPainting>));`
  
If I create the store before, and then tried setting the List<Painting>, the paintings are not added.  
  `[Create List<Painting>]`  
  `storeRepository.save(new Store("Store 4", 6));`  
  `storeRepository.getOne(4L).setPaintings(paintings);`

## RestController.java

  - When a new painting is "posted", I create the new date in RestController.java. Is there any way to create it via an annotation? The idea is an annotation indicating that the default value is `new Date()`. 

## DuplicateStoreAdvice

Which HttpStatus code should be used when trying to add a duplicate Id painting? I've chosen `HttpStatus.CONFLICT`, which seemed to me the more appropriate, but I don't know if it might be any more appropriate status.     
