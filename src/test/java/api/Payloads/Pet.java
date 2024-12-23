package api.Payloads;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Pet {
	
	    private int id;
	    private Category category;
	    private String name;
	    private List<String> photoUrls;
	    private List<Tag> tags;
	    private String status;
	    
	    // Getters and Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<String> getPhotoUrls() {
	        return photoUrls;
	    }

	    public void setPhotoUrls(List<String> photoUrls) {
	        this.photoUrls = photoUrls;
	    }

	    public List<Tag> getTags() {
	        return tags;
	    }

	    public void setTags(List<Tag> tags) {
	        this.tags = tags;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    // Nested Classes for Category and Tag
	    public static class Category {
	        private int id;
	        private String name;

	        public int getId() {
	            return id;
	        }

	        public void setId(int id) {
	            this.id = id;
	        }

	        public String getName() {
	            return name;
	        }

	        public void setName(String name) {
	            this.name = name;
	        }
	    }

	    public static class Tag {
	        private int id;
	        private String name;

	        public long getId() {
	            return id;
	        }

	        public void setId(int id) {
	            this.id = id;
	        }

	        public String getName() {
	            return name;
	        }

	        public void setName(String name) {
	            this.name = name;
	        }
	    }
	    
	    
	    
	    public static String getRandomStatus() {
	    	
	        List<String> categories = Arrays.asList("available", "pending", "sold");
	        int randomIndex = ThreadLocalRandom.current().nextInt(categories.size());
	        return categories.get(randomIndex);
	    }
	    
	    
	    
	    public static String selectImage() {
	    	
	    	File folder = new File(System.getProperty("user.dir")+"/src/test/resources/petAnimalImages");
	    	
	    	File[] files = folder.listFiles((dir, name) -> name.endsWith(".jfif"));
	    	
	    	Random random = new Random();
	    	int randomIndex = random.nextInt(files.length);
	    	
	    	File randomImage = files[randomIndex];
	    	
	    	
	    	String path = randomImage.getAbsolutePath();
            return path;
	    	
	    	
	    }    

}
