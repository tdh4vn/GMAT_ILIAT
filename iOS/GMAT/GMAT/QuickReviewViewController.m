//
//  QuickReviewViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuickReviewViewController.h"
#import "QuickReviewCell.h"
#import "StudentAnswer.h"
#import "QuickReviewAnswerCell.h"
#import "Question.h"
#import "Constant.h"
#import "LoginViewController.h"
#import "ReviewViewController.h"

@interface QuickReviewViewController ()

@end

@implementation QuickReviewViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.navigationItem.hidesBackButton = YES;
    
    self.title = @"Review";
    
    _tbvQuickReview.tableFooterView = [[UIView alloc]init];
    
    UIBarButtonItem *barItem = [[UIBarButtonItem alloc]initWithTitle:@"Done" style:UIBarButtonItemStyleBordered target:self action:@selector(btnDoneDidTouch)];
    self.navigationItem.rightBarButtonItem = barItem;
    
}

#pragma mark - TableView Datasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (section == 0) {
        return 1;
    } else if (section == 1) {
        return _questions.count;
    } else {
        return 0;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellId = @"quickReviewCell";
    
    if (indexPath.section == 0) {
        
        QuickReviewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
        
        if (!cell) {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"QuickReviewCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        
        if ([cell respondsToSelector:@selector(setSeparatorInset:)]) {
            cell.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0);
        }
        if ([cell respondsToSelector:@selector(setPreservesSuperviewLayoutMargins:)]) {
            cell.preservesSuperviewLayoutMargins = NO;
        }
        if ([cell respondsToSelector:@selector(setLayoutMargins:)]) {
            cell.layoutMargins = UIEdgeInsetsMake(0, 0, 0, 0);
        }
        
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        
        int numberOfRightAnswer = 0;
        
        for (StudentAnswer *studentAnswer in _studentAnswers) {
            if ([studentAnswer.result isEqualToNumber:Right]) {
                numberOfRightAnswer++;
            }
        }
        
        cell.viewCir.percentage = (float)numberOfRightAnswer/(float)_studentAnswers.count;
        cell.viewCir.textLabel.text = [NSString stringWithFormat:@"%d/%lu",numberOfRightAnswer,(unsigned long)_studentAnswers.count];
        
        
        return cell;
    } else if (indexPath.section == 1) {
        
        QuickReviewAnswerCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
        
        if (!cell) {
            NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"QuickReviewAnswerCell" owner:self options:nil];
            cell = [nib objectAtIndex:0];
        }
        
        cell.lblQuestion.text = [NSString stringWithFormat:@"%ld. %@",indexPath.row + 1, [_questions[indexPath.row] stimulus]];
        if ([[_studentAnswers[indexPath.row] result] isEqualToNumber:Right]) {
            cell.imvResult.image = [UIImage imageNamed:kImage_Right];
        } else {
            cell.imvResult.image = [UIImage imageNamed:kImage_Wrong];
        }
        
        return cell;
    } else {
        return nil;
    }
    
}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.section == 1) {
        [tableView deselectRowAtIndexPath:indexPath animated:NO];
        
        ReviewViewController *reviewController = [self.storyboard instantiateViewControllerWithIdentifier:@"review"];
        
        reviewController.studentAnswer = _studentAnswers[indexPath.row];
        reviewController.question = _questions[indexPath.row];
        
        [self.navigationController pushViewController:reviewController animated:YES];
        
    }
    
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.section == 0) {
        return 150;
    } else {
        return 50;
    }
}

#pragma mark - Class funtions

- (void)btnDoneDidTouch;
{
    
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
