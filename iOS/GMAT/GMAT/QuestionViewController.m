//
//  QuestionViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuestionViewController.h"
#import "Constant.h"
#import "Question.h"
#import "MagicalRecord.h"
#import "TextCell.h"
#import "Answer.h"
#import "AnswerCell.h"
#import "StudentAnswer.h"
#import "QuickReviewViewController.h"

@interface QuestionViewController ()

@property(nonatomic, assign) NSInteger displayIndex;

@property (nonatomic, strong) NSMutableArray *studentAnwsers;

@end

@implementation QuestionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    _studentAnwsers = [[NSMutableArray alloc]init];
    
    [_btnSubmit setBackgroundColor:kAppColor];
    
    [self configTableView];
    
    self.navigationItem.hidesBackButton = YES;
    
    _displayIndex = 0;
    [self redisplayQuestion];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - TableView Datasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (section == 0) {
        return 2;
    } else if (section == 1) {
        return 5;
    } else {
        return 0;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    Question *selectedQuestion = _questions[_displayIndex];
    NSArray *answers = [NSArray arrayWithArray:[selectedQuestion.answers allObjects]];
    static NSString *cellId = @"question";
    
    if (indexPath.section == 0) {
        TextCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
        if (!cell) {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"TextCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        
        if (indexPath.row == 0) {
            cell.lblText.text = selectedQuestion.stimulus;
        } else if (indexPath.row == 1) {
            if ([selectedQuestion.stem isEqualToString:@""]) {
                cell.hidden = YES;
            }
            cell.lblText.text = selectedQuestion.stem;
        }
        
        return cell;
        
    } else if (indexPath.section == 1) {
        AnswerCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
        if (!cell) {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"AnswerCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        
        if (indexPath.row == 0) {
            cell.lblAnswer.text = [(Answer *)answers[0] choice];
            cell.imvAnswer.image = [[UIImage imageNamed:kImage_AnswerA] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
        } else if (indexPath.row == 1) {
            cell.lblAnswer.text = [(Answer *)answers[1] choice];
            cell.imvAnswer.image = [[UIImage imageNamed:kImage_AnswerB] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
        } else if (indexPath.row == 2) {
            cell.lblAnswer.text = [(Answer *)answers[2] choice];
            cell.imvAnswer.image = [[UIImage imageNamed:kImage_AnswerC] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
        } else if (indexPath.row == 3) {
            cell.lblAnswer.text = [(Answer *)answers[3] choice];
            cell.imvAnswer.image = [[UIImage imageNamed:kImage_AnswerD] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
        } else if (indexPath.row == 4) {
            cell.lblAnswer.text = [(Answer *)answers[4] choice];
            cell.imvAnswer.image = [[UIImage imageNamed:kImage_AnswerE] imageWithRenderingMode:UIImageRenderingModeAlwaysTemplate];
        }
        
        return cell;
        
    } else return nil;
    
}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(nonnull NSIndexPath *)indexPath {
    //[tableView deselectRowAtIndexPath:indexPath animated:NO];
    
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:tableView.indexPathForSelectedRow];
    
    [cell setSelected:YES];
}

#pragma mark - Class funtions

- (void)redisplayQuestion;
{
    self.title = [NSString stringWithFormat:@"%ld/%lu",_displayIndex + 1,(unsigned long)_questions.count];
    [_tbvQuestion reloadData];
}

- (void)configTableView;
{
    UIImageView *backgroundView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:kImage_TableQuestionPacksBackground]];
    [backgroundView setFrame:self.tbvQuestion.frame];
    
    self.tbvQuestion.backgroundView = backgroundView;
    
    self.tbvQuestion.tableFooterView = [[UIView alloc]init];
    
    _tbvQuestion.estimatedRowHeight = 60.0f;
    _tbvQuestion.rowHeight = UITableViewAutomaticDimension;
    
}

- (IBAction)btnSubmitDidTouch:(id)sender {
    
    if (_tbvQuestion.indexPathForSelectedRow) {
        if (_displayIndex < _questions.count - 1) {
            Question *question = _questions[_displayIndex];
            
            StudentAnswer *newStudentAnswer = [StudentAnswer createStudentAnswerWithChoiceIndex:_tbvQuestion.indexPathForSelectedRow.row andQuestion:question];
            [_studentAnwsers addObject:newStudentAnswer];
            
            if (_displayIndex == _questions.count - 2) {
                
                [_btnSubmit setTitle:@"Submit" forState:UIControlStateNormal];
                
            }
            
            _displayIndex+=1;
            [self redisplayQuestion];
        } else {
            
            Question *question = _questions[_displayIndex];
            
            StudentAnswer *newStudentAnswer = [StudentAnswer createStudentAnswerWithChoiceIndex:_tbvQuestion.indexPathForSelectedRow.row andQuestion:question];
            [_studentAnwsers addObject:newStudentAnswer];
            
            QuickReviewViewController *quickReviewController = [self.storyboard instantiateViewControllerWithIdentifier:@"quickReview"];
            
            quickReviewController.studentAnswers = _studentAnwsers;
            quickReviewController.questions = _questions;
            
            CATransition* transition = [CATransition animation];
            transition.duration = 1.0f;
            transition.type = kCATransitionMoveIn;
            transition.subtype = kCATransitionFade;
            [self.navigationController.view.layer addAnimation:transition
                                                        forKey:kCATransition];
            
            [self.navigationController pushViewController:quickReviewController animated:NO];
        }
        
    }
    
}
@end
