Feature: Adding new place
@Addplace
Scenario Outline: successfully adding a new place
Given sending request payload with place information "<Name>" "<Language>" "<Address>" "<Phonenumber>" "<Accuracy>" "<Website>" "<Latitude>" "<Longitude>" "<types>"
When triggering the Http "Post" method for "AddplaceApi" with resources attached
Then validating the response with status code
And also validating the "status" is "OK"
And validating the "scope" is "APP"
And using the PlaceID retrieve the info and maps to "<Name>" by "GetplaceApi"
#And using the PlaceID deleting the created place using "DELETE" resource by "DeleteplaceApi"


Examples:

| Name  | Language | Address     | Phonenumber | Accuracy | Website        | Latitude  |  Longitude | | types         |
| Vicky | Tamil    | Poonamallee | 9176441288  | 100      | www.google.com | 38.383494 | 33.427362  | | shoe park,shop|
#| giri  | Telugu   | chennai     | 9841871788  | 200      | www.image.com  | 45.684    | 89.87945   | | rest,park     |                                                                        

@Deleteplace
 Scenario: Delete place api
 Given using the PlaceID deleting the created place using "DELETE" resource by "DeleteplaceApi"
 