//
//  QuestionViewController.m
//  GMAT
//
//  Created by Trung Đức on 3/14/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuestionViewController.h"
#import "Question.h"
#import "QuestionPackCell.h"
#import "QuestionCell.h"

@interface QuestionViewController ()

@property (nonatomic, strong) Question *displayQuestion;
@property (nonatomic, strong) NSMutableDictionary *answers;
@property int selectedRow;
@property int result;

@end

@implementation QuestionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    _answers = [[NSMutableDictionary alloc]init];
    
//    for (Question *question in _questions) {
//        _answers
//    }
    
    _result = 0;
    
    [self.navigationItem setHidesBackButton:YES];
    
    _tbvQuestion.tableFooterView = [[UIView alloc]init];
    
    _tbvQuestion.estimatedRowHeight = 60.0f;
    _tbvQuestion.rowHeight = UITableViewAutomaticDimension;
    
    _displayQuestion = _questions[0];
    
    [self displayQuestion:_displayQuestion];
    
    UIBarButtonItem *rightBarItem = [[UIBarButtonItem alloc]initWithTitle:@"Next" style:UIBarButtonItemStyleBordered target:self action:@selector(btnNextDidTouch)];
    self.navigationItem.rightBarButtonItem = rightBarItem;
    
}

#pragma mark - TableView Datasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (section == 0) {
        return 2;
    } else {
        return _displayQuestion.answers.count;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
            static NSString *cellId = @"answerCell";
    QuestionCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
    if (!cell) {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"QuestionCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    
            cell.selectionStyle = UITableViewCellSelectionStyleNone;
    
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            
            
            cell.lblText.text = _displayQuestion.stimulus;
        } else {
            cell.lblText.text = _displayQuestion.stem;
        }
        
        
        
        return cell;
        
    } else {

        
        switch (indexPath.row) {
            case 0:
                cell.lblText.text = [NSString stringWithFormat:@"A . %@",_displayQuestion.answers[indexPath.row]];
                break;
            case 1:
                cell.lblText.text = [NSString stringWithFormat:@"B . %@",_displayQuestion.answers[indexPath.row]];
                break;
            case 2:
                cell.lblText.text = [NSString stringWithFormat:@"C . %@",_displayQuestion.answers[indexPath.row]];
                break;
            case 3:
                cell.lblText.text = [NSString stringWithFormat:@"D . %@",_displayQuestion.answers[indexPath.row]];
                break;
            case 4:
                cell.lblText.text = [NSString stringWithFormat:@"E . %@",_displayQuestion.answers[indexPath.row]];
                break;
            default:
                break;
        }

        
        if ((int)indexPath.row == _selectedRow) {
            cell.lblText.textColor = [UIColor redColor];
        } else {
            cell.lblText.textColor = [UIColor blackColor];
        }
        
        return cell;
    }
}


- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.section == 1) {
        _selectedRow = (int)indexPath.row;
        
        CGPoint contentOffset = tableView.contentOffset;
        
        [_tbvQuestion reloadData];
        
        _tbvQuestion.contentOffset = contentOffset;
        
    }
    
}


#pragma mark - Class funtion

- (void)btnPreviousDidTouch;
{
    if ([_questions indexOfObject:_displayQuestion] == 0) {
        _displayQuestion = [_questions lastObject];
    } else {
        _displayQuestion = _questions[[_questions indexOfObject:_displayQuestion] - 1];
    }
    
    [self displayQuestion:_displayQuestion];
    
}

- (void)btnNextDidTouch;
{
    
    if ([_questions indexOfObject:_displayQuestion] == _questions.count - 1) {
        _displayQuestion = [_questions firstObject];
    } else {
        _displayQuestion = _questions[[_questions indexOfObject:_displayQuestion] + 1];
    }
    
    if ([_questions indexOfObject:_displayQuestion] == _questions.count - 1) {
        UIBarButtonItem *rightBarItem = [[UIBarButtonItem alloc]initWithTitle:@"Submit" style:UIBarButtonItemStyleBordered target:self action:@selector(btnSubmitDidTouch)];
        self.navigationItem.rightBarButtonItem = rightBarItem;
    }
    
    if (_selectedRow == [_displayQuestion.rightAnswerIdx intValue]) {
        _result +=1;
    }
    
    _selectedRow = -1;
    
    [self displayQuestion:_displayQuestion];
    
}

- (void)displayQuestion:(Question *)question;
{
    _selectedRow = -1;
    self.title = [NSString stringWithFormat:@"%lu/10",[_questions indexOfObject:_displayQuestion] + 1];
    [_tbvQuestion reloadData];
    
}

- (void)btnSubmitDidTouch;
{
    if (_selectedRow == [_displayQuestion.rightAnswerIdx intValue]) {
        _result +=1;
    }
    
    _selectedRow = -1;
    _tbvQuestion.hidden = YES;
    self.navigationItem.rightBarButtonItem = nil;
    self.title = @"Submit";
    _lblResult.text = [NSString stringWithFormat:@"%d/%lu",_result,_questions.count];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
