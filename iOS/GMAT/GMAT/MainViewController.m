//
//  MainViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/6/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "MainViewController.h"
#import "MMDrawerBarButtonItem.h"
#import "UIViewController+MMDrawerController.h"
#import "Constant.h"
#import "QuestionPackCell.h"
#import "GmatAPI.h"
#import "QuestionPack.h"
#import "MagicalRecord.h"
#import "Question.h"
#import "QuestionViewController.h"

@interface MainViewController()

@property (nonatomic, assign) BOOL updated;

@end

@implementation MainViewController

- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    self.title = kTitle_MainView;
    
    _updated = NO;
    
    _questionPacks = [[NSMutableArray alloc]init];
    
    [self loadQuestionPacks];
    
    [self loadQuestions];
    
    [self setupLeftMenuButton];
    
    [self configTableView];
    
}

#pragma mark - TableView Datasource

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _questionPacks.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *cellId = @"QeustionPackCell";
    
    QuestionPackCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
    
    if (!cell) {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"QuestionPackCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    QuestionPack *quetionPack = _questionPacks[indexPath.row];
    
    cell.lblDate.text = quetionPack.availableTime;
    cell.progessView.tintColor = kAppColor;
    cell.progessView.progress = 0.2;
    
    return cell;
    
}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:NO];
    
    if ([Question MR_countOfEntities] != 0 || _updated == YES) {
        
        QuestionViewController *questionViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"question"];
        
        CATransition* transition = [CATransition animation];
        transition.duration = 0.3f;
        transition.type = kCATransitionMoveIn;
        transition.subtype = kCATransitionFromTop;
        [self.navigationController.view.layer addAnimation:transition
                                                    forKey:kCATransition];
        
        questionViewController.questions = [[NSMutableArray alloc]init];
        for (NSString *questionId in [_questionPacks[indexPath.row] questionIds]) {
            Question *question = [Question MR_findFirstByAttribute:@"questionId" withValue:questionId];
            [questionViewController.questions addObject:question];
        }
        
        [self.navigationController pushViewController:questionViewController animated:NO];
        
    }
}

#pragma mark - Class funtions

+ (MainViewController *)sharedInstance;
{
    static MainViewController *sharedInstance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
        sharedInstance = [storyboard instantiateViewControllerWithIdentifier:@"mainView"];
    });
    return sharedInstance;
}

- (void)setupLeftMenuButton{
    MMDrawerBarButtonItem * leftDrawerButton = [[MMDrawerBarButtonItem alloc] initWithTarget:self action:@selector(leftDrawerButtonPress:)];
    [self.navigationItem setLeftBarButtonItem:leftDrawerButton animated:YES];
}

- (void)leftDrawerButtonPress:(id)sender{
    [self.mm_drawerController toggleDrawerSide:MMDrawerSideLeft animated:YES completion:nil];
}

- (void)configTableView;
{
    UIImageView *backgroundView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:kImage_TableQuestionPacksBackground]];
    [backgroundView setFrame:self.tbvQuestionPacks.frame];
    
    self.tbvQuestionPacks.backgroundView = backgroundView;
    
    self.tbvQuestionPacks.separatorColor = kAppColor;
    
    self.tbvQuestionPacks.tableFooterView = [[UIView alloc]init];
    
    self.tbvQuestionPacks.rowHeight = 90.0f;
}

- (void)loadQuestionPacks;
{
    [sGmatAPI exploreQuestionPackWithCompletionBlock:^(NSArray *questionPacks) {
        
        for (NSDictionary *jsonDict in questionPacks) {
            QuestionPack *newQuestionPack = [[QuestionPack alloc]initWithJson:jsonDict];
            [_questionPacks addObject:newQuestionPack];
        }
        
        [_tbvQuestionPacks reloadData];
        
    }];
}

- (void)loadQuestions;
{
    [sGmatAPI exploreQuestionWithCompletionBlock:^(NSArray *question) {
        _updated = YES;
    }];
}

@end
