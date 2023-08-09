package in.fssa.technolibrary.model;

public abstract class CategoryEntity implements Comparable<CategoryEntity>{
	
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
	
    @Override
    public int compareTo(CategoryEntity other) {
		return Integer.compare(this.id, other.getId());
    }
	
	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", name=" + name + "]";
	}

}
