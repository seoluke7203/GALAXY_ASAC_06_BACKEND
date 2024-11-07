package asac06.galaxy.model;

public enum ProductGenre {
    MUSICAL, THEATER, CONCERT;

    // 추후 수정
    public static ProductGenre of(String genre) {
        for (ProductGenre productGenre : ProductGenre.values()) {
            if(productGenre.toString().equals(genre.toUpperCase())) {
                return productGenre;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + genre);
    }
}
