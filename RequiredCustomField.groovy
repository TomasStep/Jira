/*
    Script written to make a field required if other field is selected
    due Issue creation stage and setup on postfunction
*/
import com.atlassian.jira.component.ComponentAccessor;
import com.opensymphony.workflow.InvalidInputException;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption

String personFieldId = 'customfield_xxxxx'
String singleChoiceFieldId = 'customfield_xxxxx'

String errorMessage = 'Value for custom field must be specified'

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def personField = customFieldManager.getCustomFieldObject(personFieldId)
def singleChoiceField = customFieldManager.getCustomFieldObject(singleChoiceFieldId)

if(!singleChoiceField || !personField) return

def person = issue.getCustomFieldValue(personField)
def singleChoice = issue.getCustomFieldValue(singleChoiceField) as String
def isOtherChosen = singleChoice == "Yes"

if(isOtherChosen && !person)
{
    throw new InvalidInputException(errorMessage); 
}
/*
    Known mistakes and issues:
        1) Add script call after issue creation stage in postfunction
*/