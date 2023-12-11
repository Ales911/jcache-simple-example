
class City {

    private String name;
    private String locatedIn;

    public City(String name, String locatedIn) {
        this.name = name;
        this.locatedIn = locatedIn;
    }

    public String getName() {
        return name;
    }

    public String getLocatedIn() {
        return locatedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }

        City city = (City) o;

        if (!name.equals(city.name)) {
            return false;
        }
        return locatedIn.equals(city.locatedIn);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + locatedIn.hashCode();
        return result;
    }
}