import com.atlassian.jira.component.ComponentAccessor

def issue = ComponentAccessor.issueManager.getIssueObject("SD-4")


// just a temp test to reach data
log.info("Test:")
log.info("Summary: " + issue.summary)
log.info("Desc.: " + issue.description)

def assigneeId = issue.getAssigneeId();
log.info("Assignee: " + assigneeId)
