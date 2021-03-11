# Web Practicum, part II
    ... in process ...

# Web Practicum, part I

## Application topic
Information about the personnel of the company

## Text results
Application pages and user scenarious descriptions are contained in 'Documentation' folder. 

## Database scheme
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/scheme_of_database.png)

## Application pages
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/application_scheme_simplified.png)
Full text scheme is alo contained in 'Documentation' folder, also file 'App_Pages' contains text description.

### Main page (1)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Main.png)
Button "Departments" linked with page 2, Departments

Button "Staff" linked with page (8), Staff

### Departments page (2)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Departments.png)
Contains button "main", linked with page 1, Main page.

Contains button “add department”, linked with page 4, Department edit. 

Contains list of all departments. Each department name is linked with page 3, Department info. 

Contains search panel, which, after using, changed departments list in accordance with the imposed restrictions.
Search parameters: Department name, department size.

### Department info page (3)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Department_Info.png)
Contains “Delete” button, which deletes current department and returns user on page 2, Departments page.

Contains “Edit” button, which moves user on page 4, Department edit page.

Contains “Return” button, which moves user on page 2, Departments page.

Contains “Name” filed with current department name.

Contains “Director” field, with current department director name, linked with according page 9, Staff member info page.

Contains “Head department” field, with current department head department name, linked with according page 3, Department info page.

Contains list of sub-departments, each of them is linked with according page 3, Department info page.

Contains list of positions. If any staff member holds a position, his name is placed near position name and is linked with according page 9, Staff member info page.
Each position is connected with page 6, Staff member assignment page.

### Department edit page (4)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Department_Edit.png)

Contains “Return” button, which moves user on page 3, Department info page.

Contains “Confirm” button, which saves result and moves user on page 3, Department info page.

Contains “Add position” button, linked with page 5, Position edit page.

Contains editable “Name” field.

Contains “Director” button, connected with page 6, Staff member assignment page.

Contains “Head department” button, connected with page 7, Department assignment page.

Contains positions list, each is clickable and connected with page 5, Position edit page.

### Position edit page (5)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Position_Edit.png)

Contains editable fileds “Name”, “Size” and “Duties”.

Contains “Return” button, which moves user on page 4, Department edit page.

Contains “Confirm” button, which saves result and moves user on page 4, Department edit page.

### Staff member assignment page (6)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Staff_Member_Assignment.png)

Contains “Return” button, which moves user on page 4, Department edit page.

Contain list of staff members. Each is clickable and connected with page 4, Department edit page. 

Contains staff search panel. Changing search parameters changes list of staff members.

### Department assignment page (7)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Department_Assignment.png)

Contains list of all departments. Each department name is linked with page 4, Department edit page. 

Contains search panel, which, after using, changed departments list in accordance with the imposed restrictions. Search parameters: Department name, department size.

Contains “Return” button, connected with page 4, Department edit page.

### Staff page (8)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Staff.png)

Contains button "main", linked with page 1, Main page.

Contains button “add staff member”, linked with page 10, Staff member edit page. 

Contains list of all staff members. Each staff member name is linked with page 9, Staff member info page. 

Contains search panel, which, after using, changed staff members list in accordance with the imposed restrictions. 
Search parameters: Staff member name, date of employment.

### Staff member info page (9)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Staff_Member_Info.png)
Contains fields with staff member information.

Contains “Delete” button, connected with page 8, Staff page.

Contains “Return” button, connected with page 8, Staff page.

Contains list of held positions with button “Show history”, which adds last positions. Position departments are clickable and connected with page 3, Department info page.

### Staff member edit page (10)
![alt text](https://github.com/GetsuDer/cmcWebPrac/blob/main/Documentation/App_Pages_Pictures/page_Staff_Member_Edit.png)

Contains editable fields with staff member information.

Contains “Return” button, connected with page 9,  Staff member info page.

Contains “Confirm” button, connected with page 9, Staff member info page.

## User scenarios
###  Department info
#### Get department structure
Main -> departments -> search department -> department info
#### Get department employees list
Main -> departments -> search department -> department info
#### Get department director
Main -> departments -> search department -> department info
#### Get department positions list
Main -> departments -> search department -> department info
#### Get department position info
Main -> departments -> search department -> department info
### Change of department 
#### Add department
Main -> departments -> add department
#### Remove department
Main -> departments -> search department -> department info -> delete
#### Change department info
Main -> departments -> search department -> department info -> edit -> department edit page
#### Add staff member on a position
Main -> departments -> search department -> department info -> positions list -> staff member assignment
#### Remove employee from a position
Main -> departments -> search department -> department info -> positions list -> fire button
#### Add position
Main -> departments -> search department -> department info -> edit -> department edit page -> add position -> position edit page
#### Remove position
Main -> departments -> search department -> department info -> edit -> department edit page -> position edit page -> remove
#### Change position info
Main -> departments -> search department -> department info -> edit -> department edit page -> position edit page
### Staff info
#### Get staff members list
Main -> staff -> search staff members
#### Get staff member info
Main -> staff -> search staff members -> staff member info
### Change of staff
#### Add staff member
Main -> staff -> add member
#### Remove staff member
Main -> staff -> search staff members -> staff member info -> delete member
#### Change staff member info	
Main -> staff -> search staff members -> staff member info -> staff member edit
