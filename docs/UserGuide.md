---
layout: page
title: User Guide
---

Eva is a simple and lightweight application that **handles HR related administrative tasks**, 
like managing staff performance and recruitment of applicants, faster than a typical mouse/GUI driven app.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `eva.jar` from [here](https://github.com/AY2021S1-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Eva.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all staffs and applicants.

   * **`addstaff`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`addapplicant`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`delete`**`3` : Deletes the 3rd person shown in the current list.

   * **`clear`** : Clears the database.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

## List of commands you can execute <br>
[Add a staff](#adding-a-staff) <br>
[Add a applicant](#adding-an-applicant) <br>


### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

<a name="adding-a-staff">
<H3> Adding a staff: <code>addstaff</code></H3> 
</a> 

Adds a staff to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[c/COMMENTS]…`

<div markdown="span" class="alert alert-primary">
- A staff can have any number of tags (including 0) <br>
- A staff can have any number of comments (including 0) <br>
 
</div>

Examples:
* `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addstaff n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

<a name="adding-an-applicant">
<H3> Adding an applicant: <code>addapplicant</code></H3> 
</a> 

Adds an applicant to the application.

Format: `addapplicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [id/INTERVIEW_DATE] [t/TAG]…​[c/COMMENTS]…`

<div markdown="span" class="alert alert-primary">
- An applicant can have any number of tags (including 0) <br>
- An applicant can have any number of comments (including 0) <br>
- :bulb: The interview date is optional. If an interview date is not fixed yet, you can leave it and set it later. <br>

</div>

Examples:
* `addapplicant n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addapplicant n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Record leave taken by staff: `addleave`

Adds a leave to the staff that is in eva database.
Format: `addleave l/d:DATE [d:DATE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Addition of multiple leaves using the same command is supported. A leave can have either one (single day) or two dates (start and end inclusive).
</div>

Examples:
* `addleave l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020`
* `addleave l/d:10/10/2020 d:08/10/2020 l/d:09/09/2020`

### Listing all persons : `list`

Shows a list of all persons in the application.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the application.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program. 

Format: `exit`

### Saving the data

Once any change is made to the data from the commands above, the data is saved in the hard disk. 
There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Eva home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**AddStaff** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
**AddLeave** | `addleave l/d:DATE [d:DATE]` <br> e.g., `addleave l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
