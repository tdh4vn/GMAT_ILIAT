//
//  MainViewController.m
//  GMAT
//
//  Created by Trung Đức on 3/14/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "MainViewController.h"
#import "GmatAPI.h"
#import "QuestionPack.h"
#import "QuestionPackCell.h"
#import "QuestionViewController.h"
#import "Question.h"

@interface MainViewController ()

@property (nonatomic, strong) NSMutableArray *questions;
@property (nonatomic, strong) NSMutableArray *questionPacks;

@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.title = @"Question Packs";
    
    _tbvQuestionPack.tableFooterView = [[UIView alloc]init];
    
    [_tbvQuestionPack reloadData];
    
    [sGmatAPI exploreQuestionPackWithCompletionBlock:^(NSArray *questionPacks) {
        
        if (!_questionPacks) {
            _questionPacks = [[NSMutableArray alloc]init];
        }
        
        for (NSDictionary *jsonDict in questionPacks) {
            QuestionPack *newQuestionPack = [[QuestionPack alloc]initWithJson:jsonDict];
            [_questionPacks addObject:newQuestionPack];
        }
        
        NSLog(@"%lu",_questionPacks.count);
        [_tbvQuestionPack reloadData];
        
    }];
    
    [sGmatAPI exploreQuestionWithCompletionBlock:^(NSArray *questions) {
       
        if (!_questions) {
            _questions = [[NSMutableArray alloc]init];
        }
        
        for (NSDictionary *jsonDict in questions) {
            Question *newQuestion = [[Question alloc]initWithJson:jsonDict];
            [_questions addObject:newQuestion];
        }
        
        NSLog(@"%lu",_questions.count);
        
    }];
    
}

#pragma mark - TableView Datasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _questionPacks.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *cellId = @"questionPackCell";
    
    QuestionPackCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
    if (!cell) {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"QuestionPackCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    
    cell.lblAvailableTime.text = [_questionPacks[indexPath.row] availableTime];
    cell.accessoryType = UITableViewCellAccessoryNone;
    
    return cell;
}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(nonnull NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:NO];
    
    QuestionViewController *questionViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"question"];
    
    questionViewController.questionPack = _questionPacks[indexPath.row];
    questionViewController.questions = [[NSMutableArray alloc]init];
    
    for (NSString *selectedQuestionId in questionViewController.questionPack.questionIds) {
        for (Question *question in _questions) {
            if ([question.questionId isEqualToString:selectedQuestionId]) {
                [questionViewController.questions addObject:question];
            }
        }
    }
    
    CATransition* transition = [CATransition animation];
    transition.duration = 0.3f;
    transition.type = kCATransitionMoveIn;
    transition.subtype = kCATransitionFromTop;
    [self.navigationController.view.layer addAnimation:transition
                                                forKey:kCATransition];
    
    [self.navigationController pushViewController:questionViewController animated:NO];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
