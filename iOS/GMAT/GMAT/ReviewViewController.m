//
//  ReviewViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/11/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "ReviewViewController.h"
#import "Constant.h"
#import "TextCell.h"
#import "AnswerCell.h"
#import "Answer.h"
#import "ReviewAnswerCell.h"
#import "SCLAlertView.h"

@interface ReviewViewController ()

@end

@implementation ReviewViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    UIImageView *backgroundView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:kImage_TableQuestionPacksBackground]];
    [backgroundView setFrame:self.tbvReview.frame];
    
    self.tbvReview.backgroundView = backgroundView;
    
    _tbvReview.tableFooterView = [[UIView alloc]init];
    
    _tbvReview.estimatedRowHeight = 60.0f;
    _tbvReview.rowHeight = UITableViewAutomaticDimension;
    
    [self reload];
    
}

#pragma mark - TableView Datasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (section == 0) {
        return 2;
    } else {
        return 5;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {

    NSArray *answers = [NSArray arrayWithArray:[_question.answers allObjects]];
    static NSString *cellId = @"question";
    
    if (indexPath.section == 0) {
        TextCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
        if (!cell) {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"TextCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        
        if (indexPath.row == 0) {
            cell.lblText.text = _question.stimulus;
        } else if (indexPath.row == 1) {
            if ([_question.stem isEqualToString:@""]) {
                cell.hidden = YES;
            }
            cell.lblText.text = _question.stem;
        }
        
        return cell;
        
    } else if (indexPath.section == 1) {
        ReviewAnswerCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
        if (!cell) {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"ReviewAnswerCell" owner:self options:nil];
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
        
        cell.explanation = [(Answer *)answers[indexPath.row] explanation];
        
        if ((int)[_studentAnswer.answerChoiceIdx intValue] == (int)indexPath.row) {
            cell.lblAnswer.textColor = [UIColor redColor];
            cell.imvAnswer.tintColor = [UIColor redColor];
        }
        
        if ((int)[_question.rightAnswerIdx intValue] == (int)indexPath.row) {
            cell.lblAnswer.textColor = [UIColor greenColor];
            cell.imvAnswer.tintColor = [UIColor greenColor];
        }
        
        cell.line.hidden = YES;
        
        return cell;
        
    } else return nil;

}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    SCLAlertView *alert = [[SCLAlertView alloc] initWithNewWindow];
    alert.tintTopCircle = YES;
    alert.useLargerIcon = NO;
    alert.cornerRadius = 13.0f;
    alert.showAnimationType = SlideOutFromCenter;
    
    alert.customViewColor = kAppColor;
    
    [alert addButton:@"Close" actionBlock:^{
        
    }];
    
    [alert showInfo:nil subTitle:[[_question.answers allObjects][indexPath.row] explanation] closeButtonTitle:nil duration:0.0f];
    
}

#pragma mark - Class Funtion

- (void)reload;
{
    if ([_studentAnswer.result isEqual:Right]) {
        self.title = @"Correct";
    } else {
        self.title = @"Incorrect";
    }
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
