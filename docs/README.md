# User Guide
Duke is a desktop app for managing different tasks. It is optimized to be used via a Command Line Interface. Made for users who are comfortable typing and using the Command Line.

* Quick Start 
* Features 
  * Viewing help: `help`
  * Adding a todo task: `todo`
  * Adding a deadline: `deadline`
  * Adding an event: `event`
  * Deleting a task: `delete`
  * Marking a task as completed: `done`
  * Listing all tasks: `list`
  * Finding a task by keyword: `find`
  * Finding tasks by day: `today`
  * Finding tasks by month: `month`
  * Finding tasks by year: `year`
  * Exiting the program: `bye`
  * Saving data
 * FAQ
 * Command Summary

## Quick Start
1. Ensure you have Java 11 or above installed in your computer 
1. Download the latest *duke.jar* from [here](https://github.com/prachi2023/ip/releases)
1. Copy the file to the folder you want to use as the home folder for the task manager
1. Use the windows button + 'r' to open up the command prompt 
1. Enter the folder with the .jar file
1. Enter "Java -jar *"duke.jar"* 
1. It should look like this: 
![Image of command line](https://github.com/prachi2023/ip/blob/master/docs/dukeCLIstart.PNG)

## Features 
**Notes about command format**
> * Words in upper case are parameters to be input by the user
> * items in square brackets [] are optional
> * Parameters have to be input in the order mentioned

### Viewing help: `help` 
Shows all the possible commands the use can enter.
Includes the format in which the user has to enter data.

Format: `help`

### Adding a todo task: `todo`
Adds a todoTask to the task list.

Format: `todo DESCRIPTION`

Examples: 
   * `todo Fix laptop`
   * `todo Buy groceries`
   
### Adding a deadline: `deadline`
Adds a task with a deadline to the task list.

Format: `deadline DESCRIPTION by yyyy-mm-dd HH:MM`
   * Time is entered in 24 hr format
   * If no time entered, 23:59 is used as default. 
   
Examples: 
   * `deadline Do homework by 2020-10-23 16:30`
   * `deadline Submit assignment by 2020-11-6`

### Adding an event: `event`
Adds an event to the task list. 

Format: `event DESCRIPTION at yyyy-mm-dd HH:MM`
   * Time is entered in 24 hr format
   * If no time entered, 23:59 is used as default. 
   
Examples: 
   * `event Networking session at 2020-12-26 14:30`
   * `event Birthday Party at 2021-01-08`
   
### Deleting a task: `delete`
Deletes a task from the list

Format: `delete INDEX`
   * Deletes the task in the tasklist with the specified `INDEX`
   * `INDEX` has to be a positive integer within the range of the number of tasks
   * *Tip:* use the `list` function to find the index of the task to delete
   
Examples: 
   * `delete 1`
   * `delete 5`  

### Marking a task as completed: `done`
Changes a task status from incomplete to complete

Format: `done INDEX`
   * Edits the task in the tasklist with the specified `INDEX`
   * `INDEX` has to be a positive integer within the range of the number of tasks
   * *Tip:* use the `list` function to find the index of the task to edit

Examples: 
   * `done 7`
   * `done 3`
   
### Listing all tasks: `list`
Shows all the tasks currently stored in the list

Format: `list`

Example output: 

   >1. [D][Done] Submit assignment (by: 06 NOVEMBER 2020 11:59 PM)
   >2. [E][X] Networking session  (at: 26 DECEMBER 2020 02:30 PM)
   >3. [T][Done] Buy Groceries

### Finding a task by keyword: `find`
Shows all the tasks with descriptions that contain the keyword/phrase entered 

Format: `find KEYWORD`
   * Only the description is searched
   * The word does not have to match exactly as long as it is part of the description e.g `birth` will match `birthday`
  
Examples: 
   * `find Net` returns `2. [E][X] Networking session  (at: 26 DECEMBER 2020 02:30 PM)`
   * `find Submit assign` returns `1. [D][Done] Submit assignment (by: 06 NOVEMBER 2020 11:59 PM)`

###  Finding tasks by day: `today`
Shows all the tasks that are due/happening in the current day

Format: `today`
  
Example output: 
   * If the current day is 2020-11-06, returns `1. [D][Done] Submit assignment (by: 06 NOVEMBER 2020 11:59 PM)`


### Finding tasks by month: `month`
Shows all the tasks that are due/occuring in the given month 

Format: `month [INDEX]`
   * If no index has been input, it will return the tasks in the current month
   * Index has to be an integer between 0 and 12 (inclusive of both)
   * Index of value 0 will return the tasks in the current month
   * Index of value 1 to 12 will return tasks in that given month. e.g `Index = 1` returns tasks in month of `JANUARY`
   
Examples: 
   * `month 11` returns `1. [D][Done] Submit assignment (by: 06 NOVEMBER 2020 11:59 PM)`
   * `month 01` returns `4. [E][X] Birthday party (at: 08 JANUARY 2020 23:59`

### Finding tasks by year: `year`
Shows all the tasks that are due/occuring in the given year

Format: `year [INDEX]`
   * If no index has been input, it will return the tasks in the current year
   * Index has to be a positive integer 
   * Index of value 0 will return the tasks in the current year
   
Examples: 
   * `year 2020` returns `1. [D][Done] Submit assignment (by: 06 NOVEMBER 2020 11:59 PM)`
   * `year 2021` returns `4. [E][X] Birthday party (at: 08 JANUARY 2021 23:59`

### Exiting the program: `bye`
Exits the program

Format: `bye`

### Saving data
Data of the tasks is automatically saved and updated in the hard disk after any command that changes the data. 

No manually saving required.

## FAQ

**Q**: How do I transfer data from my computer to another

**A**: Follow the steps in the quick start. Copy the file "tasklist.txt" into the new folder on the new computer 


**Q**: Can I edit the time of the event once I have added it

**A**: Unfortunately, currently the only way to do so is by deleting the event and adding a new event with the updated details

## Command Summary 

Command | Format 
-------|-------
help | help
list | list
exit | exit
delete | delete INDEX
mark as done | done INDEX
**ADD TASK**|
todo | todo DESCRIPTION
deadline | deadline DESCRIPTION by yyyy-mm-dd HH:MM
event | deadline DESCRIPTION by yyyy-mm-dd HH:MM
**SEARCH TASK**|
keyword | find KEYWORD
year | year [YEAR INDEX]
month | month [MONTH INDEX]
today | today
