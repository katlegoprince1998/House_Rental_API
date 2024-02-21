# House_Rental_API


The House Rental API allows users to manage properties available for rent. It provides endpoints for CRUD operations on rental properties, including listing properties, adding new properties, updating property details, and deleting properties.

1. Get All Houses

Endpoint: GET /

Description: Retrieve a list of all available rental properties.
2. Get House by ID

Endpoint: GET /{id}

Description: Retrieve details of a specific rental property by its ID.
3. Add House

Endpoint: POST /

Description: Add a new rental property to the system.
4. Update House

Endpoint: PUT /{id}

Description: Update details of an existing rental property.
5. Delete House

Endpoint: DELETE /{id}

Description: Delete a rental property from the system.
Data Models
House Entity

6. Paginated List of Houses

Endpoint: GET /?page={page}&size={size}

Description: Retrieve a paginated list of rental properties.

    page: The page number to retrieve (default: 0).
    size: The number of items per page (default: 10).

    id: Unique identifier for the property.
    title: Title of the property listing.
    description: Description of the property.
    location: Location/address of the property.
    price: Rental price of the property.
    bedrooms: Number of bedrooms in the property.
    bathrooms: Number of bathrooms in the property.
    size: Size of the property in square footage.
    images: List of image URLs representing the property.
